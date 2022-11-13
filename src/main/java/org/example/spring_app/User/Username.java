package org.example.spring_app.User;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = { "http://localhost:3000" }, maxAge = 3600)
@Transactional
@RequestMapping("/api/username")
public class Username {

    @Secured("ROLE_ADMIN")
    @GetMapping
    public ResponseEntity<String> findAll(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(userDetails.getUsername());
    }
}
