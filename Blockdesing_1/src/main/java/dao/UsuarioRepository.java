
package dao;

import domain.Blockdesign;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Blockdesign, Long>{
    
    Blockdesign findByCorreoAndClave(String correo, String password);
    
}
