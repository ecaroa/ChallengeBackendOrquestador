package com.example.ChallengeBackendOrquestador.service;

import com.example.ChallengeBackendOrquestador.exceptions.RegistrationException;
import com.example.ChallengeBackendOrquestador.model.response.GenericResponse;
import com.example.ChallengeBackendOrquestador.service.dto.ValidEmailDTO;
import com.example.ChallengeBackendOrquestador.service.dto.ValidUsernameDTO;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Objects;

@Service
public class RegistrationService {

    public void registerUser(String nombre, String apellido, String email,
                             String telefono, LocalDate fechaNacimiento,
                             String user, String password) throws RegistrationException {
        Boolean isValidUsername = validateUsername(user);
        if (!isValidUsername) throw new RegistrationException("Usuario ingresado se encuentra en uso.");
        Boolean isValidEmail = validateEmail(email);
        if (!isValidEmail) throw new RegistrationException("El email ingresado se encuentra en uso.");
        registrateUser(nombre, apellido, email, telefono, fechaNacimiento, user, password);
    }

    private Boolean validateUsername(String username) throws RegistrationException {
        try {
            StringBuilder strb = new StringBuilder("http://localhost:8080/users/");
            strb.append(username).append("/validate");

            RestTemplate restTemplate = new RestTemplate();
            ValidUsernameDTO response = restTemplate.getForObject(strb.toString(), ValidUsernameDTO.class);
            return response.getIsValidUsername();
        } catch (Exception e) {
            throw new RegistrationException(e.getMessage());
        }
    }

    private Boolean validateEmail(String email) throws RegistrationException {
        try {
            StringBuilder strb = new StringBuilder("http://localhost:8080/personas/");
            strb.append(email).append("/validate");


            RestTemplate restTemplate = new RestTemplate();
            ValidEmailDTO response = restTemplate.getForObject(strb.toString(), ValidEmailDTO.class);
            return response.getIsValidEmail();
        }catch (Exception e) {
            throw new RegistrationException(e.getMessage());
        }
    }

    private void registrateUser(String nombre, String apellido, String email,
                                String telefono, LocalDate fechaNacimiento,
                                String user, String password) throws RegistrationException {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("nombre", nombre);
            jsonObject.put("apellido", apellido);
            jsonObject.put("email", email);
            jsonObject.put("telefono", telefono);
            jsonObject.put("fechaNacimiento", fechaNacimiento);
            jsonObject.put("user", user);
            jsonObject.put("password", password);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> request = new HttpEntity<>(jsonObject.toString(), headers);

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForObject("http://localhost:8080/auth/register", request, GenericResponse.class);
        }catch (Exception e) {
            throw new RegistrationException(e.getMessage());
        }
    }

}
