package com.fattocs.tecnico.controller;

import com.fattocs.tecnico.model.Tarefa;
import com.fattocs.tecnico.repository.TarefaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tarefas")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class TarefaController{

    @Autowired
    private TarefaRepository tarefaRepository;

    @GetMapping
    public ResponseEntity<List<Tarefa>> getAll() {
        return ResponseEntity.ok(tarefaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> getById(@PathVariable Long id) {
        return tarefaRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta)).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Tarefa> post(@Valid @RequestBody Tarefa tarefa) {
        if (tarefaRepository.existsByNome(tarefa.getNome()))
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        Tarefa novaTarefa = tarefaRepository.save(tarefa);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTarefa);
    }

    @PutMapping("/{id}") public ResponseEntity<Tarefa> put(@Valid @RequestBody Tarefa tarefa, @PathVariable Long id) {
        if (tarefaRepository.existsByNome(tarefa.getNome()))
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        return tarefaRepository.findById(id) .map(resposta -> { tarefa.setId(id); tarefaRepository.save(tarefa); return ResponseEntity.ok(tarefa); }) .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        tarefaRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id){
        Optional<Tarefa> tarefaOptional = tarefaRepository.findById(id);

        if (tarefaOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        tarefaRepository.deleteById(id);
    }
}
