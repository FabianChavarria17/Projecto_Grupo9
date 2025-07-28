
package com.Blockdesing.Dao;

import com.Blockdesing.Domain.Proyecto;
import com.Blockdesing.Domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProyectoDao extends JpaRepository<Proyecto, Long> {
    // Para obtener proyectos de un usuario
    List<Proyecto> findByUsuario(Usuario usuario);
}
