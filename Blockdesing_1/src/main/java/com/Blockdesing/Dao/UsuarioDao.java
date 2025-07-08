
package com.Blockdesing.Dao;

import com.Blockdesing.Domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDao extends JpaRepository<Usuario, Long> {
    Usuario findByCorreoAndPassword(String correo, String password);
    //para ver si el usuario existe antes de crear
    Usuario findByUserName(String userName);
}