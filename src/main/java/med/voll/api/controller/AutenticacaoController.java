package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.usuario.LoginRequest;
import med.voll.api.domain.usuario.UsuarioEntity;
import med.voll.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid LoginRequest loginRequest){
        var token = new UsernamePasswordAuthenticationToken(loginRequest.login(), loginRequest.senha());
        var authentication = manager.authenticate(token);
        return ResponseEntity.ok(tokenService.gerarToken((UsuarioEntity) authentication.getPrincipal()));
    }

}
