
package com.Blockdesing.Controller;

import com.Blockdesing.Dao.UsuarioDao;
import com.Blockdesing.Domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/listaUsuarios")
public class AdminController {

    @Autowired
    private UsuarioDao usuarioDao;

    @GetMapping
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioDao.findAll());
        return "listaUsuarios"; // listaUsuarios.html
    }

    @PostMapping("/suspender/{id}")
    public String suspenderUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioDao.findById(id).orElse(null);
        if (usuario != null) {
            usuario.setActivo(false);
            usuarioDao.save(usuario);
        }
        return "redirect:/admin/usuarios";
    }

    @PostMapping("/activar/{id}")
    public String activarUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioDao.findById(id).orElse(null);
        if (usuario != null) {
            usuario.setActivo(true);
            usuarioDao.save(usuario);
        }
        return "redirect:/admin/usuarios";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        usuarioDao.deleteById(id);
        return "redirect:/admin/usuarios";
    }
}
