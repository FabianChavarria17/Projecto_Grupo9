package com.Blockdesing.Domain;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    private String nombre;
    private String correo;
    private String rol;
    private boolean activo;
    private String cvPath;    // para almacenar ruta o nombre
    
    //Examen
    @Column(name = "examen1_aprobado")
    private boolean examen1Aprobado;
    
    @Column(name = "examen2_aprobado")
    private boolean examen2Aprobado;
    
    public Usuario() {
    }

    public Usuario(String userName, String password, String nombre, String correo, String rol, boolean activo) {
        this.userName = userName;
        this.password = password;
        this.nombre = nombre;
        this.correo = correo;
        this.rol = rol;
        this.activo = activo;
    }


    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    public String getCvPath() { return cvPath; }
    public void setCvPath(String cvPath) { this.cvPath = cvPath; }
    
     public boolean isExamen1Aprobado() {
        return examen1Aprobado;
    }

        //Examen

    public void setExamen1Aprobado(boolean examen1Aprobado) {
        this.examen1Aprobado = examen1Aprobado;
    }

    public boolean isExamen2Aprobado() {
        return examen2Aprobado;
    }

    public void setExamen2Aprobado(boolean examen2Aprobado) {
        this.examen2Aprobado = examen2Aprobado;
    }
}

