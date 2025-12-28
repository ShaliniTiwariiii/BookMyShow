package org.example.bookmyshow.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.example.bookmyshow.exceptions.UserAlreadyExistsException;
import org.example.bookmyshow.exceptions.UserNotFoundException;
import org.example.bookmyshow.exceptions.WrongPasswordException;
import org.example.bookmyshow.model.Session;
import org.example.bookmyshow.model.SessionStatus;
import org.example.bookmyshow.model.User;
import org.example.bookmyshow.repositories.SessionRepository;
import org.example.bookmyshow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final SecretKey secretKey;

    public AuthService(UserRepository userRepository,
                       SessionRepository sessionRepository,
                       BCryptPasswordEncoder passwordEncoder,
                       @Value("${jwt.secret}") String jwtSecret) {

        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.passwordEncoder = passwordEncoder;
        this.secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    // ================= SIGN UP =================
    public boolean signUp(String email, String password) throws UserAlreadyExistsException {

        if (userRepository.findByEmail(email).isPresent()) {
            throw new UserAlreadyExistsException(
                    "User with email " + email + " already exists"
            );
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        userRepository.save(user);
        return true;
    }

    // ================= LOGIN =================
    public String login(String email, String password)
            throws UserNotFoundException, WrongPasswordException {

        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User with email: " + email + " not found.");
        }

        User user = userOptional.get();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new WrongPasswordException("Wrong password");
        }

        Date expiryDate = getExpiryDate(30);
        String token = generateJwtToken(user, expiryDate);

        Session session = new Session();
        session.setToken(token);
        session.setUser(user);
        session.setExpiringAt(expiryDate);
        session.setSessionStatus(SessionStatus.ACTIVE);

        sessionRepository.save(session);

        return token;
    }

    // ================= TOKEN VALIDATION =================
    public User validateTokenAndGetUser(String token) {

        // Verify JWT
        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token);

        // Check active session
        Session session = sessionRepository
                .findByTokenAndSessionStatus(token, SessionStatus.ACTIVE)
                .orElseThrow(() -> new RuntimeException("Invalid or expired session"));

        // Expiry validation
        if (session.getExpiringAt().before(new Date())) {
            session.setSessionStatus(SessionStatus.ENDED);
            sessionRepository.save(session);
            throw new RuntimeException("Session expired");
        }

        return session.getUser();
    }

    // ================= LOGOUT =================
    public void logout(String token) {

        Session session = sessionRepository
                .findByTokenAndSessionStatus(token, SessionStatus.ACTIVE)
                .orElseThrow(() -> new RuntimeException("Invalid session"));

        session.setSessionStatus(SessionStatus.ENDED);
        sessionRepository.save(session);
    }

    // ================= JWT CREATION =================
    private String generateJwtToken(User user, Date expiryDate) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("user_id", user.getId());
        claims.put("email", user.getEmail());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(secretKey)
                .compact();
    }

    private Date getExpiryDate(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }
}
