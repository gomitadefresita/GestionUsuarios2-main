package com.gestionusuario.usuario.model.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {
    private int idUsuario;
    @NotBlank(message = "El nombre y apellido es obligatorio.")
    private String nombreUsuario, apellidoUsuario;
    @NotBlank(message = "El correo debe ser válido.")
    private String correoUsuario;
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres.")
    private String password;

}
