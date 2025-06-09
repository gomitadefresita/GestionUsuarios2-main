package com.gestionusuario.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionusuario.usuario.entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer>{
    UsuarioEntity findByCorreoUsuario(String correoUsuario);
    Boolean existsByCorreoUsuario(String correoUsuario);
    void deleteByCorreoUsuario(String correo);
    UsuarioEntity findByidUsuario(int idUsuario);
    Boolean existsById(int id);
}