
package com.Blockdesing.Controller;

import com.Blockdesing.Domain.Usuario;
import com.Blockdesing.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
public class RecuperarPasswordController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/recuperarPassword")
    public String mostrarFormularioRecuperar() {
        return "recuperarPassword";
    }

    @PostMapping("/recuperarPassword")
    public String procesarRecuperacion(@RequestParam("correo") String correo, Model model) {
        Usuario usuario = usuarioService.buscarPorCorreo(correo);
        if (usuario == null) {
            model.addAttribute("error", "No existe una cuenta con ese correo.");
            return "recuperarPassword";
        }

        String token = UUID.randomUUID().toString();
        usuarioService.guardarTokenRecuperacion(usuario.getIdUsuario(), token);

        String enlace = "http://localhost:8080/restablecerPassword?token=" + token;

        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(correo);
        mensaje.setSubject("Recupera tu contraseña");
        mensaje.setText("Haz clic aquí para restablecer tu contraseña:\n" + enlace);
        mailSender.send(mensaje);

        model.addAttribute("mensaje", "Hemos enviado un enlace a tu correo para restablecer tu contraseña.");
        return "recuperarPassword";
    }

    @GetMapping("/restablecerPassword")
    public String mostrarFormularioRestablecer(@RequestParam("token") String token, Model model) {
        Usuario usuario = usuarioService.buscarPorToken(token);
        if (usuario == null) {
            model.addAttribute("error", "Token inválido o expirado.");
            return "recuperarPassword";
        }
        model.addAttribute("token", token);
        return "restablecerPassword";
    }

    @PostMapping("/restablecerPassword")
    public String procesarRestablecer(@RequestParam("token") String token,
                                      @RequestParam("nuevaPassword") String nuevaPassword,
                                      Model model) {
        Usuario usuario = usuarioService.buscarPorToken(token);
        if (usuario == null) {
            model.addAttribute("error", "Token inválido o expirado.");
            return "restablecerPassword";
        }
        usuarioService.actualizarPassword(usuario, nuevaPassword);
        model.addAttribute("mensaje", "Tu contraseña ha sido cambiada con éxito.");
        return "login";
    }
}
