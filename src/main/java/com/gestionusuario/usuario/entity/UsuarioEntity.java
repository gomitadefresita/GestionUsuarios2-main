package com.gestionusuario.usuario.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity 
@EntityScan 
@Data
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idUsuario;

    private String nombreUsuario;
    private String apellidoUsuario;
    private String correoUsuario;
    private String passwordUsuario;
    private String direccionEnvio;
    private String metodoPago;
    private boolean activo;
}
