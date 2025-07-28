
package com.Blockdesing.Controller;


import com.Blockdesing.Dao.UsuarioDao;
import com.Blockdesing.Domain.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UsuarioDao usuarioDao;
    
    @GetMapping
    public String showLoginPage() {
        return "login";            // login.html en templates/
    }
    
     @PostMapping
    public String login(
            @RequestParam String correo,
            @RequestParam String password,
            RedirectAttributes redirectAttributes,
            HttpSession session) {

        Usuario usuario = usuarioDao.findByCorreoAndPassword(correo, password);
        if (usuario != null && usuario.isActivo()) {
            session.setAttribute("usuarioLogueado", usuario);
            redirectAttributes.addFlashAttribute("mensaje",
                "Login exitoso. ¡Bienvenido " + usuario.getNombre() + "!");
            return "redirect:/";    // página home
        } else {
            redirectAttributes.addFlashAttribute("error",
                "Correo o contraseña incorrectos.");
            return "redirect:/login"; // de nuevo al login
        }
    }

//    @PostMapping("/login")
//    public String login(@RequestParam String correo, @RequestParam String password,RedirectAttributes redirectAttributes,
//            HttpSession session) {
//        Usuario usuario = usuarioDao.findByCorreoAndPassword(correo, password);
//        if (usuario != null && usuario.isActivo()) {
//            // Guardar usuario en sesión (opcional)
//            session.setAttribute("usuarioLogueado", usuario);
//            // Redireccionar al inicio con mensaje
//            redirectAttributes.addFlashAttribute("mensaje", "Login exitoso. ¡Bienvenido " + usuario.getNombre() + "!");
//            return "redirect:/index";
//            /*return "Login exitoso. Bienvenido, " + usuario.getNombre();*/
//        } else {
//            // Redireccionar a login con error
//            redirectAttributes.addFlashAttribute("error", "Correo o contraseña incorrectos.");
//            return "redirect:/login";
//            /*return "Credenciales inválidas o cuenta inactiva";*/
//        }
//    }
}
