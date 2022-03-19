package com.quantox.awsmock.rest;

import com.quantox.awsmock.service.AuthService;
import com.quantox.awsmock.service.dto.AuthRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthResource {

    private final AuthService authService;

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthRequestDTO authRequestDTO){
        return ResponseEntity.ok(authService.authenticate(authRequestDTO));
    }

}
