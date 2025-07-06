
package controller;

import dao.UsuarioRepository;
import domain.Blockdesign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class loginController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @PostMapping("/login")
    public String login(@RequestParam String correo, @RequestParam String password) {
        Blockdesign usuario = usuarioRepository.findByCorreoAndClave(correo, password);
        if (usuario != null) {
            return "Login exitoso. Bienvenido, ";
        } else {
            return "Credenciales inválidas";
        }
    }
    
}
