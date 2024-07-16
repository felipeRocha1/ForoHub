package com.alura.foro.api.Control;

import com.alura.foro.api.Dto.Seguridad.JWTtoken;
import com.alura.foro.api.Dto.usuario.AutentificarUsuario;
import com.alura.foro.api.Modelo.Usuario;
import com.alura.foro.api.Seguridad.TokenServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class ControlAutentificacion {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenServicio tokenServicio;


    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid AutentificarUsuario datosAutenticacion) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacion.email(), datosAutenticacion.contrasena());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenServicio.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new JWTtoken(JWTtoken));
    }
}
