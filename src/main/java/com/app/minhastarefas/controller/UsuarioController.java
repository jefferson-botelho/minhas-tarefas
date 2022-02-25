package com.app.minhastarefas.controller;

import com.app.minhastarefas.dominio.Usuario;
import com.app.minhastarefas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/usuario/{id}")
    public Usuario obterUsuarioPorId(@PathVariable Long id) {
        return service.obterUsuarioPorId(id);
    }
}
