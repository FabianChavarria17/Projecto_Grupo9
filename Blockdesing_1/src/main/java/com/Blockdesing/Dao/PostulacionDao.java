
package com.Blockdesing.Dao;

import com.Blockdesing.Domain.Postulacion;
import com.Blockdesing.Domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostulacionDao extends JpaRepository<Postulacion, Long> {
    List<Postulacion> findByUsuario(Usuario usuario);
}
