package com.example.ChallengeBackendOrquestador.controller;

import com.example.ChallengeBackendOrquestador.exceptions.LoginException;
import com.example.ChallengeBackendOrquestador.model.request.LoginRequest;
import com.example.ChallengeBackendOrquestador.model.response.GenericResponse;
import com.example.ChallengeBackendOrquestador.service.LoginService;
import com.example.ChallengeBackendOrquestador.service.dto.LoginDTO;
import jakarta.validation.Valid;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("auth/login")
    public ResponseEntity<GenericResponse> login(@Valid @RequestBody LoginRequest body) throws LoginException {
        try {
            Boolean loginOk = loginService.login(body.getUser(), body.getPassword());
            if (!loginOk){
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new GenericResponse("Usuario y/o contraseña incorrecto."));
            }
            return ResponseEntity.status(HttpStatus.OK).body(new GenericResponse("Login realizado correctamente."));
        } catch (Exception e) {
            throw new LoginException("Ocurrió un error al llevar a cabo el login.");
        }
    }
}
