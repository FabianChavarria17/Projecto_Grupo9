package com.Blockdesing.Controller;

import com.Blockdesing.Service.OfertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    /* @RequestMapping("/") //url al que respone
    public String index() {
        //model.addAttribute("attribute", "value");
        return "index"; // nombre de la vista en template a mostrar
    }*/
    @Autowired
    private OfertaService ofertaService; // <-- Inyectamos el servicio

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("ofertas", ofertaService.listarOferta()); // <-- Aquí pasa la lista a la vista
        return "index";
    }

    @RequestMapping("/info")
    public String info() {
        //model.addAttribute("attribute", "value");
        return "info";
    }

    @RequestMapping("/login")
    public String login() {
        //model.addAttribute("attribute", "value");
        return "login";
    }

    @RequestMapping("/registro")
    public String registro() {
        //model.addAttribute("attribute", "value");
        return "registro";
    }

    @RequestMapping("/perfil")
    public String perfil() {
        //model.addAttribute("attribute", "value");
        return "perfil";
    }

}
