/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Blockdesing.Service;

import com.Blockdesing.Dao.OfertaDao;
import com.Blockdesing.Domain.Oferta;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OfertaServiceImpl implements OfertaService {

    private final OfertaDao ofertaDao;

    public OfertaServiceImpl(OfertaDao ofertaDao) {
        this.ofertaDao = ofertaDao;
    }

    @Override
    public List<Oferta> listarOferta() {
        return ofertaDao.findAll();
    }

    @Override
    public List<Oferta> buscarTitulo(String titulo) {
        return ofertaDao.findByTituloContainingIgnoreCase(titulo);

    }

    @Override
    public void guardarOferta(Oferta oferta) {
        ofertaDao.save(oferta);
    }

    @Override
    public void eliminarOferta(Integer id) {
        ofertaDao.deleteById(id);
    }

}
