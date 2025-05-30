package com.gestionusuario.usuario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestionusuario.usuario.model.Usuario;
import com.gestionusuario.usuario.model.Dto.UsuarioDto;
import com.gestionusuario.usuario.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Este endpoint permite agregar usuarios")
    @PostMapping("/crearUsuario")
    public ResponseEntity<String> obtenerUsuario(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.crearUsuario(usuario));

    }
    @GetMapping("/obtenerUsuario/correo/{correoUsuario}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable String correoUsuario) {
        Usuario usuario = usuarioService.obtenerUsuario(correoUsuario);
        if(usuario != null){
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/obtenerUsuario/{idUsuario}")
    public ResponseEntity<UsuarioDto> obtenerUsuarioPorId(@PathVariable int idUsuario){
        if(usuarioService.obtenerUsuarioDto(idUsuario)!= null){
            return ResponseEntity.ok(usuarioService.obtenerUsuarioDto(idUsuario));
        }
        return ResponseEntity.notFound().build();
        }
    @DeleteMapping("/eliminarUsuario/{idUsuario}")
    public String borrarUsuario(@PathVariable int idUsuario){
        return usuarioService.borrarUsuario(idUsuario);
    }
    
}