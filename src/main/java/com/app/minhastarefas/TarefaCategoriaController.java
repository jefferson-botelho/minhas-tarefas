package com.app.minhastarefas;

import com.app.minhastarefas.dominio.TarefaCategoria;
import com.app.minhastarefas.service.TarefaCategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/categorias")
public class TarefaCategoriaController {

    @Autowired
    private TarefaCategoriaService service;

    @GetMapping
    public List<TarefaCategoria> listarTodasCategorias(@RequestParam Map<String, String> parametros) {

        if(parametros.isEmpty()) {
            return service.listarTodasCategorias();
        }
        String categoria = parametros.get("categoria");
        return service.obterCategoriaPorNome(categoria);
    }

    @GetMapping("/{id}")
    public TarefaCategoria retornarTarefaPorId(@PathVariable Long id) {
        return service.obterCategoriaPorId(id);
    }

    @PostMapping
    public TarefaCategoria criarCategoria(@RequestBody TarefaCategoria tarefaCategoria) {
        return service.criarCategoria(tarefaCategoria);
    }

    @DeleteMapping("/{id}")
    public void deletarCategoria(@PathVariable Long id) {
        service.deletarCategoriaPorId(id);
    }
}
