package com.Blockdesing.Controller;

import com.Blockdesing.Dao.ProyectoDao;
import com.Blockdesing.Domain.Proyecto;
import com.Blockdesing.Domain.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PerfilController {

    @Autowired
    private ProyectoDao proyectoDao;

    @GetMapping("/perfil")
    public String perfil(HttpSession session, Model model) {
        Usuario usr = (Usuario) session.getAttribute("usuarioLogueado");
        if (usr == null) {
            return "redirect:/login";
        }
        // Carga proyectos de este usuario
        List<Proyecto> proyectos = proyectoDao.findByUsuario(usr);
        model.addAttribute("usuario", usr);
        model.addAttribute("proyectos", proyectos);
        return "perfil";
    }
}

//import com.Blockdesing.Domain.Proyecto;
//import com.Blockdesing.Domain.Usuario;
//import com.Blockdesing.Service.UsuarioService;
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//// Esta clase maneja las solicitudes para /perfil
//@Controller
//public class PerfilController {
//
//    @Autowired
//    private UsuarioService usuarioService;
//
//    @GetMapping("/perfil")
//    public String verPerfil(Model model) {
//        // Simular un usuario (no tenemos login aún)
//        Usuario usuario = new Usuario();
//        usuario.setNombre("donUser");
//        usuario.setCorreo("donUser@gmail.com");
//
//        // Simular una lista de proyectos (mock)
//        List<Proyecto> proyectos = List.of(
//            new Proyecto("Orquídea 1"),
//            new Proyecto("Orquídea 2"),
//            new Proyecto("Orquídea 3")
//        );
//
//        // Agregamos los datos al modelo para usarlos en perfil.html
//        model.addAttribute("usuario", usuario);
//        model.addAttribute("proyectos", proyectos);
//
//        return "perfil"; 
//    }
//    
//    
//    
//}



