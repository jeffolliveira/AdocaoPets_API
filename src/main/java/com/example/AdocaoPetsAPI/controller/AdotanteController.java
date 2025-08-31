package com.example.AdocaoPetsAPI.controller;

import com.example.AdocaoPetsAPI.model.Adotante;
import com.example.AdocaoPetsAPI.repository.AdotanteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adotantes")
public class AdotanteController {

    private final AdotanteRepository adotanteRepository;

    public AdotanteController(AdotanteRepository adotanteRepository) {
        this.adotanteRepository = adotanteRepository;
    }

    // Listar todos os adotantes
    @GetMapping
    public List<Adotante> listarTodos() {
        return adotanteRepository.findAll();
    }

    // Buscar adotante por ID
    @GetMapping("/{id}")
    public ResponseEntity<Adotante> buscarPorId(@PathVariable Long id) {
        return adotanteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Adicionar novo adotante com validação
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Adotante adicionar(@Valid @RequestBody Adotante adotante) {
        return adotanteRepository.save(adotante);
    }

    // Atualizar um adotante
    @PutMapping("/{id}")
    public ResponseEntity<Adotante> atualizar(@PathVariable Long id, @Valid @RequestBody Adotante adotanteDetalhes) {
        return adotanteRepository.findById(id)
                .map(adotante -> {
                    adotante.setNome(adotanteDetalhes.getNome());
                    adotante.setEmail(adotanteDetalhes.getEmail());
                    adotante.setCpf(adotanteDetalhes.getCpf());
                    adotante.setTelefone(adotanteDetalhes.getTelefone());
                    adotante.setEndereco(adotanteDetalhes.getEndereco());
                    Adotante atualizado = adotanteRepository.save(adotante);
                    return ResponseEntity.ok(atualizado);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Deletar um adotante
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!adotanteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        adotanteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
