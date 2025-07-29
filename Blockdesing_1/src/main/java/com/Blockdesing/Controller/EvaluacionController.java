/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.Blockdesing.Controller;

import com.Blockdesing.Domain.Usuario;
import com.Blockdesing.Dao.UsuarioDao;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EvaluacionController {

    @Autowired
    private UsuarioDao usuarioDao;

    @GetMapping("/evaluaciones")
    public String mostrarEvaluaciones(HttpSession session, Model model) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        
        if (usuario == null) {
            return "redirect:/login"; /
        }
        
        Usuario usuarioActualizado = usuarioDao.findById(usuario.getIdUsuario()).orElse(null);
        
        if (usuarioActualizado == null) {
            return "redirect:/login"; 
        }
        
        model.addAttribute("usuario", usuarioActualizado);
        return "evaluaciones";
    }
}