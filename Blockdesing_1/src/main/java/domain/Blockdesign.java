
package domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

public class Blockdesign implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Usuario")
    private Long idUsuario; //lo trasnforma en id_Categoria
    private String userName;
    private String password;
    private String nombre;
    private String correo;
    private String rol;
    private boolean  activo;
    
    public Blockdesign() {
    }

    public Blockdesign(String userName, String password, String nombre, String correo, String rol, boolean activo) {
        this.userName = userName;
        this.password = password;
        this.nombre = nombre;
        this.correo = correo;
        this.rol = rol;
        this.activo = activo;
    }
    
    
    
    
}
