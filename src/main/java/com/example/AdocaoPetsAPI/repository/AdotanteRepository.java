package com.example.AdocaoPetsAPI.repository;

import com.example.AdocaoPetsAPI.model.Adotante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdotanteRepository extends JpaRepository<Adotante, Long> {
}
