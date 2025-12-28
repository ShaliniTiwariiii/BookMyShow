package org.example.bookmyshow.controller;


import org.example.bookmyshow.dto.*;
import org.example.bookmyshow.model.User;
import org.example.bookmyshow.services.AuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/sign_up")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto request) {
        SignUpResponseDto response = new SignUpResponseDto();

        try {
            boolean success = authService.signUp(request.getEmail(), request.getPassword());

            response.setRequestStatus(success ? RequestStatus.SUCCESS : RequestStatus.FAILURE);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.setRequestStatus(RequestStatus.FAILURE);
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request) {

        LoginResponseDto response = new LoginResponseDto();

        try {
            String token = authService.login(request.getEmail(), request.getPassword());
            response.setRequestStatus(RequestStatus.SUCCESS);

            HttpHeaders headers = new HttpHeaders();
            headers.add("AUTH_TOKEN", token);

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .body(response);

        } catch (Exception e) {
            response.setRequestStatus(RequestStatus.FAILURE);

            return ResponseEntity
                    .badRequest()
                    .body(response);
        }
    }


    @GetMapping("/validate")
    public boolean validate(@RequestParam("token") String token) {
        try {
            User user = authService.validateTokenAndGetUser(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

