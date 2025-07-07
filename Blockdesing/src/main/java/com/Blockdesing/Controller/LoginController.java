

package com.Blockdesing.Controller;


import com.Blockdesing.Dao.UsuarioDao;
import com.Blockdesing.Domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private UsuarioDao usuarioDao;

    @PostMapping("/login")
    public String login(@RequestParam String correo, @RequestParam String password) {
        Usuario usuario = usuarioDao.findByCorreoAndPassword(correo, password);
        if (usuario != null && usuario.isActivo()) {
            return "Login exitoso. Bienvenido, " + usuario.getNombre();
        } else {
            return "Credenciales inválidas o cuenta inactiva";
        }
    }
}

