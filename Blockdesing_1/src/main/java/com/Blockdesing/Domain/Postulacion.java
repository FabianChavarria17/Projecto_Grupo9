
package com.Blockdesing.Domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "postulacion")
public class Postulacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPostulacion;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_oferta")
    private Oferta oferta;

    private LocalDateTime fechaPostulacion = LocalDateTime.now();

    public Postulacion() {
    }    

    public Long getIdPostulacion() { return idPostulacion; }
    public void setIdPostulacion(Long idPostulacion) { this.idPostulacion = idPostulacion; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Oferta getOferta() { return oferta; }
    public void setOferta(Oferta oferta) { this.oferta = oferta; }

    public LocalDateTime getFechaPostulacion() { return fechaPostulacion; }
    public void setFechaPostulacion(LocalDateTime fechaPostulacion) { this.fechaPostulacion = fechaPostulacion; }
}

