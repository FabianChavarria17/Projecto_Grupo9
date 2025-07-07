/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.Blockdesing.Controller;

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

}

    

