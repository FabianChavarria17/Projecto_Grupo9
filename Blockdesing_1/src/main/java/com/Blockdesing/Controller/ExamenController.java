
package com.Blockdesing.Controller;

import com.Blockdesing.Domain.*;
import com.Blockdesing.Dao.UsuarioDao; 
import jakarta.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/evaluaciones")
public class ExamenController {

    @Autowired
    private UsuarioDao usuarioDao;

    @GetMapping("/examen1")
    public String mostrarExamen1(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/login";
        }

        // Preguntas
        List<Pregunta> preguntas = Arrays.asList(
            new Pregunta(1L, "¿Cuál es el Comando para publicar varios PDFs a la vez?", null,
                Arrays.asList(
                    new Opcion(1L, "Print", false),
                    new Opcion(2L, "Publish", true),
                    new Opcion(3L, "Plot", false)
                )),
            new Pregunta(2L, "¿A cuales comandos pertenece este conjunto?", "seleccion1.png", 
                Arrays.asList(
                    new Opcion(4L, "Modify", true),
                    new Opcion(5L, "Properties", false),
                    new Opcion(6L, "Groups", false)
                ))
        );

        model.addAttribute("preguntas", preguntas);
        return "evaluaciones/examen1";
    }

    @PostMapping("/examen1/resultado")
    public String procesarResultado(@RequestParam(required = false) List<Long> respuestas,
                                  HttpSession session,
                                  RedirectAttributes redirectAttributes) {
        
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/login";
        }

        // No funciono esto!
        boolean aprobado = respuestas != null && respuestas.contains(2L) && respuestas.contains(5L);

        if (aprobado) {
            usuario.setExamen1Aprobado(true);
            usuarioDao.save(usuario);
            redirectAttributes.addFlashAttribute("mensaje", "¡Examen aprobado! Ahora puedes tomar el Examen 2.");
        } else {
            redirectAttributes.addFlashAttribute("error", "Examen no aprobado. Revisa tus respuestas.");
        }

        return "redirect:/evaluaciones";
    }
}
