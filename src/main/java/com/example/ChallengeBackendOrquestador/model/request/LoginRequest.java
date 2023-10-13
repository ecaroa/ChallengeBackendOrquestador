package com.example.ChallengeBackendOrquestador.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotEmpty
    @Size(min=3, max=50, message = "'user' must be between 3 and 50 characters")
    private String user;
    @NotEmpty
    @Pattern(regexp = "[a-zA-Z]{3,50}", message = "field 'password' must be valid")
    private String password;
}
