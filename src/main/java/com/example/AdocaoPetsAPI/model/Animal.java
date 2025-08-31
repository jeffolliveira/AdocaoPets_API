package com.example.AdocaoPetsAPI.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String tipo; // Ex: "Cachorro", "Gato"
    private String porte; // Ex: "Pequeno", "Médio", "Grande"
    private int idade;
    private String localizacao; // Ex: "São Paulo, SP"
    private String status; // Ex: "Disponível", "Adotado"
    private String necessidadesEspeciais;
}
