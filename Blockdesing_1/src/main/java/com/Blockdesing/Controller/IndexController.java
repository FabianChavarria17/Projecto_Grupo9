
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
    
    @RequestMapping("/login")
    public String login() {
        //model.addAttribute("attribute", "value");
        return "login";
    }
    
    @RequestMapping("/registro")
    public String registro() {
        //model.addAttribute("attribute", "value");
        return "hola";
    }

}

    
