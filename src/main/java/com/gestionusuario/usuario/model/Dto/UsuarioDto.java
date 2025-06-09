package com.gestionusuario.usuario.model.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {
    private int idUsuario;
    private String nombreUsuario, apellidoUsuario;
    private String correoUsuario;
    private String password;
    private String direccionEnvio;
    private String metodoPago;
    private boolean activo;

}
