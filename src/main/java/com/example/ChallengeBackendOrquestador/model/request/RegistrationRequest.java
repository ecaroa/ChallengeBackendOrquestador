package com.example.ChallengeBackendOrquestador.model.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {

    @NotEmpty(message = "The field 'nombre' cannot be empty")
    @Size(min=3, max=50, message = "'nombre' must be between 3 and 50 characters")
    private String nombre;
    @NotEmpty(message = "The field 'apellido' cannot be empty")
    @Size(min=3, max=50, message = "'apellido' must be between 3 and 50 characters")
    private String apellido;
    @NotEmpty(message = "The field 'email' cannot be empty")
    @Email(message = "Email should be valid")
    @Size(min=3, max=75, message = "'email' must be between 3 and 75 characters")
    private String email;
    @NotEmpty(message = "The field 'telefono' cannot be empty")
    @Size(min=3, max=50, message = "'telefono' must be between 3 and 50 characters")
    private String telefono;
    @NotNull(message = "The field 'fechaNacimiento' cannot be null")
    private LocalDate fechaNacimiento;
    @NotEmpty(message = "The field 'user' cannot be empty")
    @Size(min=3, max=50, message = "'user' must be between 3 and 50 characters")
    private String user;
    @NotEmpty(message = "The field 'password' cannot be empty")
    @Pattern(regexp = "[a-zA-Z]{3,50}", message = "field 'password' must be valid")
    private String password;

}
