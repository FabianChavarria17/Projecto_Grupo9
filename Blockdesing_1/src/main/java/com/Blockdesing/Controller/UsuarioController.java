
package com.Blockdesing.Controller;

import com.Blockdesing.Dao.UsuarioDao;
import com.Blockdesing.Domain.Usuario;
import com.Blockdesing.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private UsuarioDao usuarioDao;

    // Listado de usuarios
    @GetMapping("/listado")
    public String listado(Model model) {
        var usuarios = usuarioService.getUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "listaUsuarios"; // 
    }

    @PostMapping("/reactivar")
    public String reactivar(@RequestParam("idUsuario") Long idUsuario,
                            RedirectAttributes redirectAttributes) {
        Usuario usuario = usuarioService.getUsuarios(idUsuario);
        if (usuario != null) {
            usuario.setActivo(true); 
            usuarioService.save(usuario);
            redirectAttributes.addFlashAttribute("mensaje", "Usuario" + usuario.getUserName() + 
                    "reactivado correctamente.");
        }else{
            redirectAttributes.addFlashAttribute("error", "No se encontro ningun usuario");
        }
        return "redirect:/listaUsuarios";
    }

    // Suspender usuario, solo cambiar estado a inactivo
    @PostMapping("/suspender")
    public String suspender(@RequestParam("idUsuario") Long idUsuario,
                            RedirectAttributes redirectAttributes) {
        Usuario usuario = usuarioService.getUsuarios(idUsuario);
        if (usuario != null) {
            usuario.setActivo(false); 
            usuarioService.save(usuario);
            redirectAttributes.addFlashAttribute("mensaje", "Usuario suspendido correctamente.");
        }
        return "redirect:/listaUsuarios";
    }
}
