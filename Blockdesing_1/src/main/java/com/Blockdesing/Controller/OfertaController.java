/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.Blockdesing.Controller;


import com.Blockdesing.Domain.Oferta;
import com.Blockdesing.Service.OfertaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ofertas")
public class OfertaController {

    private final OfertaService ofertaService;

    public OfertaController(OfertaService ofertaService) {
        this.ofertaService = ofertaService;
    }

    // Mostrar lista de ofertas (vista administrativa)
    @GetMapping
    public String listarOfertas(Model modelo) {
        modelo.addAttribute("ofertas", ofertaService.listarOferta());
        return "redirect:/"; // Esta es la vista que tendrás que crear
    }

    // Mostrar formulario para crear nueva oferta
    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model modelo) {
        modelo.addAttribute("oferta", new Oferta());
        return "formulario"; // Vista para crear/editar
    }

    // Guardar nueva oferta o actualizar existente
    @PostMapping("/guardar")
    public String guardarOferta(@ModelAttribute("oferta") Oferta oferta) {
        ofertaService.guardarOferta(oferta);
        return "redirect:/ofertas";
    }

    // Mostrar formulario para editar oferta existente
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Integer id, Model modelo) {
        Oferta oferta = ofertaService.listarOferta()
                .stream()
                .filter(o -> o.getIdOferta().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));

        modelo.addAttribute("oferta", oferta);
        return "formulario";
    }

    // Eliminar oferta
    @GetMapping("/eliminar/{id}")
    public String eliminarOferta(@PathVariable("id") Integer id) {
        ofertaService.eliminarOferta(id);
        return "redirect:/ofertas";
    }
    
    // Ver detalle de una oferta
@GetMapping("/{id}")
public String verDetalle(@PathVariable("id") Integer id, Model modelo) {
    Oferta oferta = ofertaService.listarOferta()
            .stream()
            .filter(o -> o.getIdOferta().equals(id))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));

    modelo.addAttribute("oferta", oferta);
    return "detalle"; // nueva vista detalle.html
}

}

