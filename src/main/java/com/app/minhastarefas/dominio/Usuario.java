package com.app.minhastarefas.dominio;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "usuario")
@Getter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String usuario;

    private String senha;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Tarefa> tarefas;
}
