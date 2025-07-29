package com.Blockdesing.Controller;

import com.Blockdesing.Domain.Proyecto;
import com.Blockdesing.Domain.Usuario;
import com.Blockdesing.Service.ProyectoService;
import com.Blockdesing.Service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class PerfilController {

    @Value("${app.upload.dir:${user.home}/uploads}")
    private String uploadDir;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProyectoService proyectoService;

    @GetMapping("/perfil")
    public String perfil(HttpSession session, Model model) {
        Usuario usr = (Usuario) session.getAttribute("usuarioLogueado");
        if (usr == null) {
            return "redirect:/login";
        }
        // Carga proyectos de este usuario
        List<Proyecto> proyectos = proyectoService.listarPorUsuario(usr);
        model.addAttribute("usuario", usr);
        model.addAttribute("proyectos", proyectos);
        return "perfil";
    }

    @PostMapping("/perfil/upload")
    public String subirCv(@RequestParam("file") MultipartFile file,
            @RequestParam(value = "descripcion", required = false) String descripcion,
            HttpSession session,
            RedirectAttributes ra) throws IOException {
        Usuario usr = (Usuario) session.getAttribute("usuarioLogueado");
        if (usr == null) {
            return "redirect:/login";
        }
        String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        // Crear directorios y guardar el archivo
        Path target = Paths.get(uploadDir).resolve(filename);
        Files.createDirectories(target.getParent());
        file.transferTo(target.toFile());

        // Guarda la ruta en el usuario y actualiza
        usr.setCvPath(filename);
        usuarioService.save(usr);

        ra.addFlashAttribute("mensaje", "CV subido correctamente.");
        return "redirect:/perfil";
    }

    // Formulario para agregar un proyecto
    @GetMapping("/perfil/proyecto/nuevo")
    public String nuevoProyectoForm(HttpSession session, Model model) {
        Usuario usr = (Usuario) session.getAttribute("usuarioLogueado");
        if (usr == null) {
            return "redirect:/login";
        }
        model.addAttribute("proyecto", new Proyecto());
        return "proyecto";
    }

    // Procesar creación de proyecto
    @PostMapping("/perfil/proyecto/nuevo")
    public String guardarProyecto(@ModelAttribute Proyecto proyecto,
            HttpSession session,
            RedirectAttributes ra) {
        Usuario usr = (Usuario) session.getAttribute("usuarioLogueado");
        if (usr == null) {
            return "redirect:/login";
        }

        proyecto.setUsuario(usr);
        proyectoService.guardar(proyecto);
        ra.addFlashAttribute("mensaje", "Proyecto agregado exitosamente.");
        return "redirect:/perfil";
    }

    // Formulario para editar datos de Usuario
    @GetMapping("/perfil/editar")
    public String editarUsuarioForm(HttpSession session, Model model) {
        Usuario usr = (Usuario) session.getAttribute("usuarioLogueado");
        if (usr == null) {
            return "redirect:/login";
        }
        model.addAttribute("usuario", usr);
        return "usuario";
    }

    // Procesar edición de Usuario
    @PostMapping("/perfil/editar")
    public String actualizarUsuario(@ModelAttribute Usuario usuario,
            HttpSession session,
            RedirectAttributes ra) {
        Usuario usrSession = (Usuario) session.getAttribute("usuarioLogueado");
        if (usrSession == null) {
            return "redirect:/login";
        }

        // 1) Mantener ID y username originales
        usuario.setIdUsuario(usrSession.getIdUsuario());
        usuario.setUserName(usrSession.getUserName());

        // 2) Mantener password si viene vacío (como antes)
        if (usuario.getPassword() == null || usuario.getPassword().isEmpty()) {
            usuario.setPassword(usrSession.getPassword());
        }

        // 3) Persistir
        usuarioService.save(usuario);

        // 4) Actualizar sesión y feedback
        session.setAttribute("usuarioLogueado", usuario);
        ra.addFlashAttribute("mensaje", "Perfil actualizado correctamente.");
        return "redirect:/perfil";

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

