package com.repipa.css.security.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUsuario {

    @NotBlank
    private String nombreUsuario;

    @NotBlank
    private String password;

    private Boolean rememberMe;

}
