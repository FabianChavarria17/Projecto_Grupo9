package com.Blockdesing.Service;

import com.Blockdesing.Dao.UsuarioDao;
import com.Blockdesing.Domain.Usuario;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private PasswordEncoder passwordEncoder; // Para encriptar la nueva contraseña

    @Override
    public Usuario buscarPorCorreo(String correo) {
        return usuarioDao.findByCorreo(correo);
    }

    @Override
    public Usuario buscarPorToken(String token) {
        return usuarioDao.findByTokenRecuperacion(token);
    }

    @Override
    public List<Usuario> getUsuarios() {
        return usuarioDao.findAll();
    }

    @Override
    public Usuario getUsuario(Usuario usuario) {
        return usuarioDao.findById(usuario.getIdUsuario()).orElse(null);
    }
    
    //para el listado que manejara el Admin
    @Override
    public Usuario getUsuarios(Long idUsuario) {
        return usuarioDao.findById(idUsuario).orElse(null);
    }

    @Override
    public void save(Usuario usuario) {
        usuarioDao.save(usuario);
    }

    @Override
    public void delete(Usuario usuario) {
        usuarioDao.delete(usuario);
    }

    //Examen
    @Override
    public Usuario findByUsername(String username) {
        return usuarioDao.findByUserName(username);
    }

    @Override
    @Transactional
    public void guardarTokenRecuperacion(Long idUsuario, String token) {
        Usuario usuario = usuarioDao.findById(idUsuario).orElse(null);
        if (usuario != null) {
            usuario.setTokenRecuperacion(token);
            usuarioDao.save(usuario);
        }
    }

    @Override
    @Transactional
    public void actualizarPassword(Usuario usuario, String nuevaPassword) {
        usuario.setPassword(passwordEncoder.encode(nuevaPassword));
        usuario.setTokenRecuperacion(null); // Borramos token
        usuarioDao.save(usuario);
    }
}
