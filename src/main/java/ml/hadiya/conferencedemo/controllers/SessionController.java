package ml.hadiya.conferencedemo.controllers;

import jakarta.persistence.EntityNotFoundException;
import ml.hadiya.conferencedemo.models.Session;
import ml.hadiya.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionController {
    @Autowired
    private SessionRepository sessionRepository;
    @GetMapping
    public ResponseEntity<List<Session>> listSessions() {
        return ResponseEntity.ok(sessionRepository.findAll());
    }

//    @ExceptionHandler(EntityNotFoundException.class)
//    public ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
//    }

    @GetMapping("{id}")
    public ResponseEntity<Session> getSessionById(@PathVariable Long id){
        Optional<Session> session = sessionRepository.findById(id);
        return session.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public void createSession(@RequestBody Session session) {
        sessionRepository.saveAndFlush(session);
    }

    @DeleteMapping("{id}")
    public void deleteSessionById(@PathVariable Long id) {
        sessionRepository.deleteById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<Session> updateSessionById(@PathVariable Long id, @RequestBody Session session) {
        Optional<Session> optionalSession = sessionRepository.findById(id);
        if(optionalSession.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Session existingSession = optionalSession.get();
        BeanUtils.copyProperties(session, existingSession, "session_id");
        return ResponseEntity.ok(sessionRepository.saveAndFlush(existingSession));
    }
}
