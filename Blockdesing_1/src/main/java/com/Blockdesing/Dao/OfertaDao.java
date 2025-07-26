/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Blockdesing.Dao;

import com.Blockdesing.Domain.Oferta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfertaDao extends JpaRepository<Oferta, Integer> {
    List<Oferta> findByTituloContainingIgnoreCase(String titulo);
}
