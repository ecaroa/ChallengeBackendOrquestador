package com.example.ChallengeBackendOrquestador.service;

import com.example.ChallengeBackendOrquestador.exceptions.LoginException;
import com.example.ChallengeBackendOrquestador.service.dto.LoginDTO;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoginService {

    public Boolean login(String username, String password) throws LoginException {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("user", username);
            jsonObject.put("password", password);
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> request = new HttpEntity<>(jsonObject.toString(), headers);
            LoginDTO response = restTemplate.postForObject("http://localhost:8080/auth/login", request, LoginDTO.class);
            return response.getLoginOk();
        } catch (Exception e) {
            throw new LoginException("Ocurrió un error al llevar a cabo el login.");
        }
    }
}
