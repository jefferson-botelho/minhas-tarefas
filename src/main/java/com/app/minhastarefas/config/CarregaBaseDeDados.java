package com.app.minhastarefas.config;

import com.app.minhastarefas.dominio.Tarefa;
import com.app.minhastarefas.dominio.TarefaCategoria;
import com.app.minhastarefas.dominio.TarefaStatus;
import com.app.minhastarefas.dominio.Usuario;
import com.app.minhastarefas.repository.TarefaCategoriaRepository;
import com.app.minhastarefas.repository.TarefaRepository;
import com.app.minhastarefas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;

@Configuration
@Profile("dev")
public class CarregaBaseDeDados {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private TarefaCategoriaRepository categoriaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Bean
    CommandLineRunner executar() {
        return args -> {
            Usuario usuario = new Usuario();
            usuario.setNome("Admin");
            usuario.setSenha("1234");

            usuarioRepository.save(usuario);

            TarefaCategoria tarefaCategoria = new TarefaCategoria();
            tarefaCategoria.setCategoria("Estudos");

            categoriaRepository.save(tarefaCategoria);

            Tarefa tarefa = new Tarefa();
            tarefa.setDescricao("Estudar Java.");
            tarefa.setData(LocalDate.now());
            tarefa.setTarefaStatus(TarefaStatus.ABERTA);
            tarefa.setCategoria(tarefaCategoria);
            tarefa.setUsuario(usuario);

            tarefaRepository.save(tarefa);
        };
    }


}
