
package com.Blockdesing.Service;

import com.Blockdesing.Domain.Proyecto;
import com.Blockdesing.Domain.Usuario;

import java.util.List;

public interface ProyectoService {
    List<Proyecto> listarPorUsuario(Usuario usuario);
    Proyecto guardar(Proyecto proyecto);
    // (luego podrías añadir borrar, buscar por id, etc.)
}
