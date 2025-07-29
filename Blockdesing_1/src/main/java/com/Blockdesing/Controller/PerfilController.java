package com.Blockdesing.Controller;

import com.Blockdesing.Dao.ProyectoDao;
import com.Blockdesing.Dao.UsuarioDao;
import com.Blockdesing.Domain.Proyecto;
import com.Blockdesing.Domain.Usuario;
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

@Controller
public class PerfilController {

    @Value("${app.upload.dir:${user.home}/uploads}")
    private String uploadDir;
    
    @Autowired
    private UsuarioDao usuarioDao;
    
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

    @PostMapping("/perfil/upload")
    public String subirCv(@RequestParam("file") MultipartFile file,
                          @RequestParam(value = "descripcion", required = false) String descripcion,
                          HttpSession session,
                          RedirectAttributes ra) throws IOException {
        Usuario usr = (Usuario) session.getAttribute("usuarioLogueado");
        if (usr == null) {
            return "redirect:/login";
        }

        // Generar nombre único usando java.util.UUID
        String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        // Crear directorios y guardar el archivo en disco
        Path target = Paths.get(uploadDir).resolve(filename);
        Files.createDirectories(target.getParent());
        file.transferTo(target.toFile());

        // Guarda la ruta en el usuario y actualiza
        usr.setCvPath(filename);
        usuarioDao.save(usr);

        ra.addFlashAttribute("mensaje", "CV subido correctamente.");
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

