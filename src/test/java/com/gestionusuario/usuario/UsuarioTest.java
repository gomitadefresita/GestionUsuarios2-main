package com.gestionusuario.usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.gestionusuario.usuario.entity.UsuarioEntity;
import com.gestionusuario.usuario.model.Usuario;
import com.gestionusuario.usuario.repository.UsuarioRepository;
import com.gestionusuario.usuario.service.UsuarioService;



public class UsuarioTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;
    private UsuarioEntity usuarioEntity;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        usuario = new Usuario(1, "Juan", "Perez", "juan@gmail.com", "1234", "Viña del Mar", "Tarjeta Crédito", true);
        usuarioEntity = new UsuarioEntity();
        usuarioEntity.setIdUsuario(1);
        usuarioEntity.setNombreUsuario("juan");
        usuarioEntity.setApellidoUsuario("Perez");
        usuarioEntity.setCorreoUsuario("juan@gmail.com");
        usuarioEntity.setPasswordUsuario("1234");
        usuarioEntity.setDireccionEnvio("Viña del Mar");
        usuarioEntity.setMetodoPago("Tarjeta Crédito");
        usuarioEntity.setActivo(true);
    }

    @Test
    public void testAgregarUsuario_nuevo(){
        when(usuarioRepository.existsByCorreoUsuario(usuario.getCorreoUsuario())).thenReturn(false);
        when(usuarioRepository.save(any(UsuarioEntity.class))).thenReturn(usuarioEntity);

        String result = usuarioService.crearUsuario(usuario);
        assertEquals("Usuario agregado correctamente", result);

    }
    @Test
    public void testCrearUsuario_existe(){
        when(usuarioRepository.existsByCorreoUsuario(usuario.getCorreoUsuario())).thenReturn(true);

        String result = usuarioService.crearUsuario(usuario);
        assertEquals("El usuario ya existe", result);
    }

    @Test
    public void tesTraerUsuarioPorCorreo(){
        when(usuarioRepository.findByCorreoUsuario("juan@gmail.com")).thenReturn(usuarioEntity);
        Usuario result = usuarioService.traerUsuario("juan@gmail.com");
        assertNotNull(result);
        assertEquals("juan", result.getNombreUsuario());
    }

    @Test
    public void testTraerUausrioNoExiste(){
        when(usuarioRepository.findByCorreoUsuario("noexiste@gmail.com")).thenReturn(null);
        Usuario result = usuarioService.traerUsuario("noexiste@gmail.com");
        assertNull(result);
    }

    @Test
    public void actualizarUsuario_existe(){
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuarioEntity));
        when(usuarioRepository.save(any(UsuarioEntity.class))).thenReturn(usuarioEntity);

        Usuario nuevo =  new Usuario(1,"pedro","gomez","pedro@gmail.com", "1234", "Viña del Mar", "Tarjeta Crédito", true);
        String result = usuarioService.actualizarUsuario(1, nuevo);

        assertEquals("Usuario actualizado correctamente", result);
    }
    @Test
    public void borrarUsuario(){
        when(usuarioRepository.existsById(1)).thenReturn(true);
        doNothing().when(usuarioRepository).deleteById(1);
        String result = usuarioService.borrarUsuario(1);

        assertEquals("Usuario correctamente eliminado.", result);
    }
    
}
