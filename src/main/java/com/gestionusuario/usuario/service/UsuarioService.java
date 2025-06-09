package com.gestionusuario.usuario.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gestionusuario.usuario.entity.UsuarioEntity;
import com.gestionusuario.usuario.model.Usuario;
import com.gestionusuario.usuario.model.Dto.UsuarioDto;
import com.gestionusuario.usuario.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public String crearUsuario(Usuario user){
        try {
            Boolean estado = usuarioRepository.existsByCorreoUsuario(user.getCorreoUsuario());
            if (!estado){
                UsuarioEntity usuarioNuevo = new UsuarioEntity();
                usuarioNuevo.setNombreUsuario(user.getNombreUsuario());
                usuarioNuevo.setApellidoUsuario(user.getApellidoUsuario());
                usuarioNuevo.setCorreoUsuario(user.getCorreoUsuario());
                usuarioNuevo.setPasswordUsuario(user.getPasswordUsuario());
                usuarioNuevo.setDireccionEnvio(user.getDireccionEnvio());
                usuarioNuevo.setMetodoPago(user.getMetodoPago());
                usuarioNuevo.setActivo(user.isActivo());
                usuarioRepository.save(usuarioNuevo);
                return "Usuario creado exitosamente";

            }
            return "Correo ya existe.";
            
            
        } catch (Exception e) {
            return "Error al crear el usuario.";
            
        }
    }
    public Usuario obtenerUsuario(String correo) {
        try {
            UsuarioEntity usuario = usuarioRepository.findByCorreoUsuario(correo);
            if (usuario!=null){
                Usuario user = new Usuario(
                    usuario.getIdUsuario(),
                    usuario.getNombreUsuario(),
                    usuario.getApellidoUsuario(),
                    usuario.getCorreoUsuario(),
                    usuario.getPasswordUsuario(),
                    usuario.getDireccionEnvio(),
                    usuario.getMetodoPago(),
                    usuario.isActivo()
                );
                return user;
            }
            return null;
        } catch (Exception e) {
            return null;
        }

    }
    public UsuarioDto obtenerUsuarioDtoPorId(int idUsuario){
        try {
            UsuarioEntity usuario = usuarioRepository.findByidUsuario(idUsuario);
            UsuarioDto nuevousuario = new UsuarioDto(
                usuario.getIdUsuario(),
                usuario.getNombreUsuario(),
                usuario.getApellidoUsuario(),
                usuario.getCorreoUsuario(),
                usuario.getPasswordUsuario(),
                usuario.getDireccionEnvio(),
                usuario.getMetodoPago(),
                usuario.isActivo()
            );
            return nuevousuario;
        } catch (Exception e) {
            return null;
        }

    }
    // READ ALL
    public List<Usuario> obtenerUsuario() {
        List<UsuarioEntity> entidades = usuarioRepository.findAll();
        return entidades.stream()
                .map(u -> new Usuario(u.getIdUsuario(), u.getNombreUsuario(), u.getApellidoUsuario(), u.getCorreoUsuario(), 
                        u.getPasswordUsuario(), u.getDireccionEnvio(), u.getMetodoPago(), u.isActivo()))
                .collect(Collectors.toList());
    }

    // READ ONE by correo
    public Usuario traerUsuario(String correo) {
        try {
            UsuarioEntity usuario = usuarioRepository.findByCorreoUsuario(correo);
            if (usuario != null) {
                return new Usuario(
                        usuario.getIdUsuario(),
                        usuario.getNombreUsuario(),
                        usuario.getApellidoUsuario(),
                        usuario.getCorreoUsuario(),
                        usuario.getPasswordUsuario(),
                        usuario.getDireccionEnvio(),
                        usuario.getMetodoPago(),
                        usuario.isActivo()
                );
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    // READ ONE by id
    public UsuarioDto obtenerUsuarioId(int id) {
        try {
            Optional<UsuarioEntity> usuarioOpt = usuarioRepository.findById(id);
            if (usuarioOpt.isPresent()) {
                UsuarioEntity usuario = usuarioOpt.get();
                return new UsuarioDto(usuario.getIdUsuario(), usuario.getNombreUsuario(), usuario.getApellidoUsuario(), usuario.getCorreoUsuario(),
                            usuario.getPasswordUsuario(), usuario.getDireccionEnvio(), usuario.getMetodoPago(), usuario.isActivo());
            }
            return null;
        } catch (Exception e) {
            return null;
        }

    }
    public String actualizarUsuario(int id, Usuario user){
        try {
            Optional<UsuarioEntity> usuarioOpt = usuarioRepository.findById(id);
            if (usuarioOpt.isPresent()) {
                UsuarioEntity usuario = usuarioOpt.get();
                usuario.setNombreUsuario(user.getNombreUsuario());
                usuario.setApellidoUsuario(user.getApellidoUsuario());
                usuario.setCorreoUsuario(user.getCorreoUsuario());
                usuario.setPasswordUsuario(user.getPasswordUsuario());
                usuario.setDireccionEnvio(user.getDireccionEnvio());
                usuario.setMetodoPago(user.getMetodoPago());
                usuario.setActivo(user.isActivo());
                usuarioRepository.save(usuario);
                return "Usuario actualizado correctamente.";
            }
            return "Usuario no encontrado.";
        } catch (Exception e) {
            return "Error al actualizar usuario: " + e.getMessage();
        }
    }

    public String borrarUsuarioPorId(int idUsuario){
        try {
            UsuarioEntity usuario = usuarioRepository.findByidUsuario(idUsuario);
            if (usuario != null){
                usuarioRepository.delete(usuario);
                return "Usuario eliminado exitosamente.";
            }
            return "El usuario no existe";
        } catch (Exception e) {
            return "Error al eliminar el usuario";
        }

        }
        public String borrarUsuarioPorCorreo(String correo) {
        try {
            if (usuarioRepository.existsByCorreoUsuario(correo)) {
                usuarioRepository.deleteByCorreoUsuario(correo);
                return "Usuario correctamente eliminado.";
            }
            return "Usuario no encontrado.";
        } catch (Exception e) {
            return "Error al borrar usuario: " + e.getMessage();
        }
        }
        public ResponseEntity<UsuarioDto> obtenerUsuarioDtoPorCorreo(String correo) {
        UsuarioEntity usuario = usuarioRepository.findByCorreoUsuario(correo);
        if (usuario != null) {
            UsuarioDto usuarioResponse = new UsuarioDto(
                usuario.getIdUsuario(),
                usuario.getNombreUsuario(),
                usuario.getApellidoUsuario(),
                usuario.getCorreoUsuario(),
                usuario.getPasswordUsuario(),
                usuario.getDireccionEnvio(),
                usuario.getMetodoPago(),
                usuario.isActivo()
            );
            return ResponseEntity.ok(usuarioResponse);
        }
        return ResponseEntity.notFound().build();
    }


    public String visualizarInventario(int idUsuario){
        
        String inventarioURL = "https://tkf6gmq4-8080.brs.devtunnels.ms/obtenerProducto/1" +idUsuario;
        String inventarioData = restTemplate.getForObject(inventarioURL, String.class);

        return inventarioData;
    
    }

}

