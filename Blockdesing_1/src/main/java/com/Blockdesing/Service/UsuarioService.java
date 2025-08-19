package com.Blockdesing.Service;

import com.Blockdesing.Domain.Usuario;
import java.util.List;

public interface UsuarioService {

    public List<Usuario> getUsuarios();

    public Usuario getUsuario(Usuario usuario); 
    
    //para el listado que manejara el Admin
    public Usuario getUsuarios(Long idUsuario);

    public void save(Usuario usuario);          

    public void delete(Usuario usuario);
    
    //Examen
    Usuario findByUsername(String userName);
    
    //token para recuperar contrasena
    Usuario buscarPorCorreo(String correo);
    
    Usuario buscarPorToken (String token);
    
    public void guardarTokenRecuperacion(Long idUsuario, String token);
    
    public void actualizarPassword(Usuario usuario, String nuevaPassword);
}
