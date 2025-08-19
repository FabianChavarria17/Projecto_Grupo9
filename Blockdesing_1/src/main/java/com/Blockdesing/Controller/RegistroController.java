package com.Blockdesing.Controller;

import com.Blockdesing.Dao.UsuarioDao;
import com.Blockdesing.Domain.Usuario;
import com.Blockdesing.Service.UsuarioService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistroController {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("roles", List.of("dibujante", "constructor", "ADMIN"));
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute("usuario") Usuario usuario,
            Model model
    ) throws MessagingException {
        // Verificar si ya existe un usuario con ese username
        Usuario existente = usuarioDao.findByUserName(usuario.getUserName());
        if (existente != null) {
            model.addAttribute("mensaje", "El nombre de usuario ya esta en uso");
            //model.addAttribute("mensaje", new Usuario());
            model.addAttribute("roles", List.of("dibujante", "constructor", "ADMIN"));
            return "registro";
        }

        String passwordCodificado = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(passwordCodificado);
        //Crear nuevo usuario con activo = false + el token 
        String token = UUID.randomUUID().toString();
        usuario.setActivo(false);
        usuario.setTokenRecuperacion(token);
        
        usuarioService.save(usuario);

        // Enviar correo con el link de verificación
        String link = "http://localhost:80/verificarCuenta?token=" + token;
        enviarCorreoVerificacion(usuario.getCorreo(), link);

        model.addAttribute("mensaje", "Registro exitoso. Revisa tu correo para verificar tu cuenta.");
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("roles", List.of("dibujante", "constructor", "ADMIN"));
        return "registro";
    }

    private void enviarCorreoVerificacion(String correo, String link) throws MessagingException {
        MimeMessage mensaje = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje, true);

        helper.setTo(correo);
        helper.setSubject("Verificación de cuenta");
        helper.setText("<p>Hola,</p>"
                + "<p>Gracias por registrarte. Haz clic en el siguiente enlace para activar tu cuenta:</p>"
                + "<p><a href='" + link + "'>Activar cuenta</a></p>", true);

        mailSender.send(mensaje);
    }
    
    @GetMapping("/verificarCuenta")
    public String verificarCuenta(@RequestParam("token") String token,
                                  RedirectAttributes redirectAttributes) {
        Usuario usuario = usuarioDao.findByTokenRecuperacion(token);

        if (usuario == null) {
            redirectAttributes.addFlashAttribute("error",
                "El enlace de verificación no es válido o ha expirado.");
            return "redirect:/login";
        }

        usuario.setActivo(true);
        usuario.setTokenRecuperacion(null);
        usuarioDao.save(usuario);

        redirectAttributes.addFlashAttribute("mensaje",
            "¡Cuenta verificada con éxito! Ya puedes iniciar sesión.");
        return "redirect:/login";
    }

//        // Crear y guardar nuevo usuario
//        Usuario nuevoUsuario = new Usuario(userName, password, nombre, correo, rol, true);
//        usuarioService.save(nuevoUsuario);
//        model.addAttribute("mensaje", "Registro Exitoso");
//        model.addAttribute("usuario", new Usuario());
//        model.addAttribute("roles", List.of("dibujante", "constructor", "ADMIN"));
//        return "registro";
}
