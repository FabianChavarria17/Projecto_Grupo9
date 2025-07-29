/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.Blockdesing.Domain;

import java.util.List;

public class Pregunta {
    private Long id;
    private String texto;
    private String imagen; // Ruta opcional a imagen
    private List<Opcion> opciones;

    // Constructor completo
    public Pregunta(Long id, String texto, String imagen, List<Opcion> opciones) {
        this.id = id;
        this.texto = texto;
        this.imagen = imagen;
        this.opciones = opciones;
    }

    // Constructor sin imagen (opcional)
    public Pregunta(Long id, String texto, List<Opcion> opciones) {
        this(id, texto, null, opciones);
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public List<Opcion> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<Opcion> opciones) {
        this.opciones = opciones;
    }
}