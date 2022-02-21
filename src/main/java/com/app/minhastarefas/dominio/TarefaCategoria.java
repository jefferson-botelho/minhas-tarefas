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

    @NotBlank(message = "Campo categoria não pode estar vazio.")
    @Size(min = 5, max = 50, message = "Campo categoria deve ter entre 5 e 50 letras.")
    @Column(length = 50)
    private String categoria;
}
