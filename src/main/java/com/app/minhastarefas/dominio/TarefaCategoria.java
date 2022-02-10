package com.app.minhastarefas.dominio;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "categoria")
public class TarefaCategoria {

    @Id
    private Long id;

    private String categoria;
}
