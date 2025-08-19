package com.Blockdesing.Controller;

import com.Blockdesing.Dao.OfertaDao;
import com.Blockdesing.Dao.PostulacionDao;
import com.Blockdesing.Dao.UsuarioDao;
import com.Blockdesing.Domain.Oferta;
import com.Blockdesing.Domain.Postulacion;
import com.Blockdesing.Domain.Usuario;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostulacionController {

    @Autowired
    private PostulacionDao postulacionDao;

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private OfertaDao ofertaDao;

    @PostMapping("/postular")
    public String postular(@RequestParam ("idOferta") Integer idOferta, Principal principal,RedirectAttributes redirectAttributes) {
        Usuario usuario = usuarioDao.findByCorreo(principal.getName());
        Oferta oferta = ofertaDao.findById(idOferta).orElse(null);

        if (usuario != null && oferta != null) {
            Postulacion post = new Postulacion();
            post.setUsuario(usuario);
            post.setOferta(oferta);
            postulacionDao.save(post);
            redirectAttributes.addFlashAttribute("mensajeExito", "Postulacion Exitosa!");

        } else {
            redirectAttributes.addFlashAttribute("mensajeError", "no se pudo completar la postulacion");
        }
        return "redirect:/ofertas";
    }

    @GetMapping("/postulaciones")
    public String listarPostulaciones(Model model, Principal principal) {
        Usuario usuario = usuarioDao.findByCorreo(principal.getName());
        List<Postulacion> postulaciones = postulacionDao.findByUsuario(usuario);
        model.addAttribute("postulaciones", postulaciones);
        return "/postulaciones";
    }

}
