package org.example.bookmyshow.repositories;


import org.example.bookmyshow.model.Session;
import org.example.bookmyshow.model.SessionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {
    Session save(Session session);

    Optional<Session> findByTokenAndSessionStatus(String token, SessionStatus sessionStatus);
}