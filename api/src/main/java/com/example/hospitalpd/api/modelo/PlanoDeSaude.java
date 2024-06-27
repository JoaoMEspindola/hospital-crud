package com.example.hospitalpd.api.modelo;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PlanosDeSaude")
@Getter
@Setter
public class PlanoDeSaude {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;

    @JsonIgnore
    @OneToMany(mappedBy = "planoDeSaude")
    private Set<FichaPaciente> fichas = new HashSet<>();
}
