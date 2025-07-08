
package com.Blockdesing.Domain;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "proyecto")
public class Proyecto implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nombreProyecto")
    private String nombreProyecto;

    public Proyecto() {
    }

    public Proyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }
}
