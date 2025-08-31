package com.example.AdocaoPetsAPI.repository;

import com.example.AdocaoPetsAPI.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    // Método para filtrar animais por tipo, porte e localização
    List<Animal> findByTipoContainingAndPorteContainingAndLocalizacaoContaining(
        String tipo, String porte, String localizacao
    );
}
