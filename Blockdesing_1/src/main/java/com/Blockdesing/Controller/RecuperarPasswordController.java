
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

    @GetMapping("/recuperar-password")
    public String mostrarFormularioRecuperar() {
        return "recuperar-password";
    }

    @PostMapping("/recuperar-password")
    public String procesarRecuperacion(@RequestParam("correo") String correo, Model model) {
        Usuario usuario = usuarioService.buscarPorCorreo(correo);
        if (usuario == null) {
            model.addAttribute("error", "No existe una cuenta con ese correo.");
            return "recuperar-password";
        }

        String token = UUID.randomUUID().toString();
        usuarioService.guardarTokenRecuperacion(usuario.getIdUsuario(), token);

        String enlace = "http://localhost:8080/restablecer-password?token=" + token;

        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(correo);
        mensaje.setSubject("Recupera tu contraseña");
        mensaje.setText("Haz clic aquí para restablecer tu contraseña:\n" + enlace);
        mailSender.send(mensaje);

        model.addAttribute("mensaje", "Hemos enviado un enlace a tu correo para restablecer tu contraseña.");
        return "recuperar-password";
    }

    @GetMapping("/restablecer-password")
    public String mostrarFormularioRestablecer(@RequestParam("token") String token, Model model) {
        Usuario usuario = usuarioService.buscarPorToken(token);
        if (usuario == null) {
            model.addAttribute("error", "Token inválido o expirado.");
            return "recuperar-password";
        }
        model.addAttribute("token", token);
        return "restablecer-password";
    }

    @PostMapping("/restablecer-password")
    public String procesarRestablecer(@RequestParam("token") String token,
                                      @RequestParam("nueva") String nuevaPassword,
                                      Model model) {
        Usuario usuario = usuarioService.buscarPorToken(token);
        if (usuario == null) {
            model.addAttribute("error", "Token inválido o expirado.");
            return "restablecer-password";
        }
        usuarioService.actualizarPassword(usuario, nuevaPassword);
        model.addAttribute("mensaje", "Tu contraseña ha sido cambiada con éxito.");
        return "login";
    }
}
