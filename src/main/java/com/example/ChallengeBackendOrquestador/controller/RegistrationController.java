package com.example.ChallengeBackendOrquestador.controller;

import com.example.ChallengeBackendOrquestador.exceptions.RegistrationException;
import com.example.ChallengeBackendOrquestador.model.request.RegistrationRequest;
import com.example.ChallengeBackendOrquestador.model.response.GenericResponse;
import com.example.ChallengeBackendOrquestador.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("auth/register")
    public ResponseEntity<GenericResponse> createUser(@Valid @RequestBody RegistrationRequest body ) throws RegistrationException {
        try {
            registrationService.registerUser(body.getNombre(), body.getApellido(),
                    body.getEmail(), body.getTelefono(), body.getFechaNacimiento(), body.getUser(), body.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body(new GenericResponse("Registro realizado correctamente"));
        } catch (Exception e) {
            throw new RegistrationException(e.getMessage());
        }
    }

}
