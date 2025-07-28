
package com.Blockdesing.Domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "proyecto")
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProyecto;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(length = 1000)
    private String descripcion;

    private LocalDate fechaFin;

    private String enlace;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    public Proyecto() {
    }

    public Long getIdProyecto() {
        return idProyecto;
    }
    public void setIdProyecto(Long idProyecto) {
        this.idProyecto = idProyecto;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public LocalDate getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
    public String getEnlace() {
        return enlace;
    }
    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

