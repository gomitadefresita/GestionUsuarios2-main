package com.gestionusuario.usuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    //CREAR usuario
    @Operation(summary = "Este endpoint permite agregar usuarios.")
    @PostMapping("/crearUsuario")
    public ResponseEntity<String> obtenerUsuario(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.crearUsuario(usuario));

    }

    // READ ONE by correo
    @GetMapping("/correo/{correo}")
    @Operation(summary = "Este endpoint permite leer usuarios por correo.")
    public ResponseEntity<Usuario> traerUsuario(@PathVariable String correo) {
        Usuario usuario = usuarioService.traerUsuario(correo);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.notFound().build();
    }

    // READ ONE by id (DTO)
    @Operation(summary = "Este endpoint permite leer usuarios DTO por ID.")
    @GetMapping("/dto/{id}")
    public ResponseEntity<UsuarioDto> obtenerUsuarioId(@PathVariable int id) {
        UsuarioDto usuarioDto = usuarioService.obtenerUsuarioId(id);
        if (usuarioDto != null) {
            return ResponseEntity.ok(usuarioDto);
        }
        return ResponseEntity.notFound().build();
    }

    // READ ONE by correo (DTO)
    @Operation(summary = "Este endpoint permite actualizar usuarios DTO por correo.")
    @GetMapping("/dto/correo/{correo}")
    public ResponseEntity<UsuarioDto> obtenerUsuarioDto(@PathVariable String correo) {
        return usuarioService.obtenerUsuarioDtoPorCorreo(correo);
    }

    // UPDATE
    @Operation(summary = "Este endpoint permite actualizar usuarios.")
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarUsuario(@PathVariable int id, @RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.actualizarUsuario(id, usuario));
    }
    // OBTENER usuario por correo
    @Operation(summary = "Este endpoint permite obtener usuarios por correo.")
    @GetMapping("/obtenerUsuario/correo/{correoUsuario}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable String correoUsuario) {
        Usuario usuario = usuarioService.obtenerUsuario(correoUsuario);
        if(usuario != null){
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.notFound().build();
    }
    // OBTENER usuario por ID
    @Operation(summary = "Este endpoint permite obtener usuarios por ID.")
    @GetMapping("/obtenerUsuario/{idUsuario}")
    public ResponseEntity<UsuarioDto> obtenerUsuarioPorId(@PathVariable int idUsuario){
        if(usuarioService.obtenerUsuarioDtoPorId(idUsuario)!= null){
            return ResponseEntity.ok(usuarioService.obtenerUsuarioDtoPorId(idUsuario));
        }
        return ResponseEntity.notFound().build();
        }
    // ELIMINAR usuario por ID
    @Operation(summary = "Este endpoint permite eliminar usuarios por ID.")
    @DeleteMapping("/eliminarUsuario/{idUsuario}")
    public String borrarUsuario(@PathVariable int idUsuario){
        return usuarioService.borrarUsuarioPorId(idUsuario);
    }
    // ELIMINAR usuario por  correo
    @Operation(summary = "Este endpoint permite eliminar usuarios por correo.")
    @DeleteMapping("/correo/{correo}")
    public ResponseEntity<String> borrarUsuarioPorCorreo(@PathVariable String correo) {
        return ResponseEntity.ok(usuarioService.borrarUsuarioPorCorreo(correo));
    }
    // VISUALIZAR inventario del usuario
    @Operation(summary = "Este endpoint permite visualizar inventario.")
    @GetMapping("/visualizarInventario/{idUsuario}")
    public ResponseEntity<String> visualizarInventario(@PathVariable int idUsuario){
        return ResponseEntity.ok(usuarioService.visualizarInventario(idUsuario));
    }
    
}