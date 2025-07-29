
package com.Blockdesing.Service;

import com.Blockdesing.Dao.ProyectoDao;
import com.Blockdesing.Domain.Proyecto;
import com.Blockdesing.Domain.Usuario;
import com.Blockdesing.Service.ProyectoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProyectoServiceImpl implements ProyectoService {

    @Autowired
    private ProyectoDao proyectoDao;

    @Override
    public List<Proyecto> listarPorUsuario(Usuario usuario) {
        return proyectoDao.findByUsuario(usuario);
    }

    @Override
    public Proyecto guardar(Proyecto proyecto) {
        return proyectoDao.save(proyecto);
    }
}
