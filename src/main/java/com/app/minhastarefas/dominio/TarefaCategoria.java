package com.app.minhastarefas.dominio;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "categoria")
public class TarefaCategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "{tarefa.categoria.not-null}")
    @Size(min = 5, max = 50, message = "{tarefa.categoria.size}")
    @Column(length = 50)
    private String categoria;
}
