package com.example.AdocaoPetsAPI.controller;

import com.example.AdocaoPetsAPI.model.Animal;
import com.example.AdocaoPetsAPI.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/animais")
public class AnimalController {

    private final AnimalRepository animalRepository;

    public AnimalController(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    // Listar todos os animais com filtros
    @GetMapping
    public List<Animal> listarTodos(
            @RequestParam(required = false, defaultValue = "") String tipo,
            @RequestParam(required = false, defaultValue = "") String porte,
            @RequestParam(required = false, defaultValue = "") String localizacao) {
        return animalRepository.findByTipoContainingAndPorteContainingAndLocalizacaoContaining(tipo, porte, localizacao);
    }

    // Buscar animal por ID
    @GetMapping("/{id}")
    public ResponseEntity<Animal> buscarPorId(@PathVariable Long id) {
        Optional<Animal> animal = animalRepository.findById(id);
        return animal.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Adicionar novo animal
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Animal adicionar(@RequestBody Animal animal) {
        return animalRepository.save(animal);
    }

    // Atualizar um animal
    @PutMapping("/{id}")
    public ResponseEntity<Animal> atualizar(@PathVariable Long id, @RequestBody Animal animalDetalhes) {
        return animalRepository.findById(id)
                .map(animal -> {
                    animal.setNome(animalDetalhes.getNome());
                    animal.setTipo(animalDetalhes.getTipo());
                    animal.setPorte(animalDetalhes.getPorte());
                    animal.setIdade(animalDetalhes.getIdade());
                    animal.setLocalizacao(animalDetalhes.getLocalizacao());
                    animal.setStatus(animalDetalhes.getStatus());
                    animal.setNecessidadesEspeciais(animalDetalhes.getNecessidadesEspeciais());
                    Animal atualizado = animalRepository.save(animal);
                    return ResponseEntity.ok(atualizado);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Deletar um animal
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!animalRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        animalRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
