/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Blockdesing.Service;

import com.Blockdesing.Domain.Oferta;
import java.util.List;

/**
 *
 * @author fabia
 */
public interface OfertaService {
    List<Oferta> listarOferta();
    List<Oferta> buscarTitulo(String titulo);
    void guardarOferta(Oferta oferta);
    void eliminarOferta(Integer id);
   
    
}
