/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.blockdesing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {

    @RequestMapping("/") //url al que respone
    public String index() {
        //model.addAttribute("attribute", "value");
        return "index"; // nombre de la vista en template a mostrar
    }
    
    @RequestMapping("/info")
    public String info() {
        //model.addAttribute("attribute", "value");
        return "info";
    }
    
    @RequestMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        //model.addAttribute("usuario", new Usuario());
        return "registro"; // Plantilla registro.html
    }

}