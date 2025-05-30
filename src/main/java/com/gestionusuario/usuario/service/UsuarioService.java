package com.gestionusuario.usuario.service;

import org.springframework.beans.factory.annotation.Autowired;
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
    private UsuarioRepository usuariorepository;

    public String crearUsuario(Usuario user){
        try {
            Boolean estado = usuariorepository.existsByCorreoUsuario(user.getCorreoUsuario());
            if (!estado){
                UsuarioEntity usuarioNuevo = new UsuarioEntity();
                usuarioNuevo.setNombreUsuario(user.getNombreUsuario());
                usuarioNuevo.setApellidoUsuario(user.getApellidoUsuario());
                usuarioNuevo.setCorreoUsuario(user.getCorreoUsuario());
                usuarioNuevo.setPasswordUsuario(user.getPasswordUsuario());
                usuarioNuevo.setDireccionEnvio(user.getDireccionEnvio());
                usuarioNuevo.setMetodoPago(user.getMetodoPago());
                usuarioNuevo.setActivo(user.isActivo());
                usuariorepository.save(usuarioNuevo);
                return "Usuario creado exitosamente";

            }
            return "Correo ya existe.";
            
            
        } catch (Exception e) {
            return "Error al crear el usuario.";
            
        }
    }
    public Usuario obtenerUsuario(String correo) {
        try {
            UsuarioEntity usuario = usuariorepository.findByCorreoUsuario(correo);
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
    public UsuarioDto obtenerUsuarioDto(int idUsuario){
        try {
            UsuarioEntity usuario = usuariorepository.findByidUsuario(idUsuario);
            UsuarioDto nuevousuario = new UsuarioDto(
                usuario.getIdUsuario(),
                usuario.getNombreUsuario(),
                usuario.getApellidoUsuario(),
                usuario.getCorreoUsuario(),
                usuario.getPasswordUsuario()
            );
            return nuevousuario;
        } catch (Exception e) {
            return null;
        }
    }
    public String borrarUsuario(int idUsuario){
        try {
            UsuarioEntity usuario = usuariorepository.findByidUsuario(idUsuario);
            if (usuario != null){
                usuariorepository.delete(usuario);
                return "Usuario eliminado exitosamente";
            }
            return "El usuario no existe";
        } catch (Exception e) {
            return "Error al eliminar el usuario";
        }
        }

    public String visualizarInventario(int idUsuario){
        
        String inventarioURL = "https://tkf6gmq4-8080.brs.devtunnels.ms/obtenerProducto/1" +idUsuario;
        String inventarioData = restTemplate.getForObject(inventarioURL, String.class);

        return inventarioData;
    
    }

}

