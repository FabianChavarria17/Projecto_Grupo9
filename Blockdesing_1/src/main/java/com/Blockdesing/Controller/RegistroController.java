package com.Blockdesing.Controller;

import com.Blockdesing.Dao.UsuarioDao;
import com.Blockdesing.Domain.Usuario;
import com.Blockdesing.Service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class RegistroController {

    @Autowired
    private UsuarioDao usuarioDao;
    
    @Autowired
    private UsuarioService usuarioService;
     
    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario()); 
        model.addAttribute("roles", List.of("dibujante", "constructor", "ADMIN"));
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(
            @RequestParam String userName,
            @RequestParam String password,
            @RequestParam String nombre,
            @RequestParam(required = false) String correo,
            @RequestParam String rol,
            Model model
    ) {
        // Verificar si ya existe un usuario con ese username
        Usuario existente = usuarioDao.findByUserName(userName);
        if (existente != null) {
            model.addAttribute("mensaje", "El nombre de usuario ya esta en uso");
            model.addAttribute("mensaje", new Usuario());
            model.addAttribute("roles", List.of("dibujante", "constructor", "ADMIN"));
            return "registro";
        }

        // Crear y guardar nuevo usuario
        Usuario nuevoUsuario = new Usuario(userName, password, nombre, correo, rol, true);
        usuarioService.save(nuevoUsuario);
        model.addAttribute("mensaje", "Registro Exitoso");
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("roles", List.of("dibujante", "constructor", "ADMIN"));
        return "registro";
    }

}