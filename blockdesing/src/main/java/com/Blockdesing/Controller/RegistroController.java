package com.Blockdesing.Controller;

import com.Blockdesing.Dao.UsuarioDao;
import com.Blockdesing.Domain.Usuario;
import com.Blockdesing.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistroController {

    @Autowired
    private UsuarioDao usuarioDao;
    
    @Autowired
    private UsuarioService usuarioService;
     
    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());  
        return "registro";
    }
    
    @PostMapping("/registro")
    public String procesarFormularioRegistro(@ModelAttribute Usuario usuario, Model model) {
        usuario.setActivo(true);
        usuarioService.save(usuario);

        model.addAttribute("mensaje", "¡Usuario registrado con éxito!");
        model.addAttribute("usuario", new Usuario()); 
        return "registro";
    }

    /*
    @PostMapping("/registro")
    public String registrarUsuario(
            @RequestParam String userName,
            @RequestParam String password,
            @RequestParam String nombre,
            @RequestParam(required = false) String correo,
            @RequestParam(defaultValue = "USER") String rol
    ) {
        // Verificar si ya existe un usuario con ese username
        Usuario existente = usuarioDao.findByUserName(userName);
        if (existente != null) {
            return "El nombre de usuario ya está en uso.";
        }

        // Crear y guardar nuevo usuario
        Usuario nuevoUsuario = new Usuario(userName, password, nombre, correo, rol, true);
        usuarioDao.save(nuevoUsuario);

        return "Registro exitoso. Bienvenido, " + nuevoUsuario.getNombre();
    }
*/
}
