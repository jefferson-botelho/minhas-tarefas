package com.app.minhastarefas.service;

import com.app.minhastarefas.dominio.TarefaCategoria;
import com.app.minhastarefas.dominio.Usuario;
import com.app.minhastarefas.repository.TarefaCategoriaRepository;
import com.app.minhastarefas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> listarTodosUsuarios() {
        return repository.findAll();
    }

    public Usuario obterUsuarioPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    public Usuario criarUsuario(Usuario usuario) {
        repository.save(usuario);

        return usuario;
    }

    public void deletarUsuarioPorId(Long id) {
        Usuario usuario = obterUsuarioPorId(id);
        repository.delete(usuario);
    }

}
