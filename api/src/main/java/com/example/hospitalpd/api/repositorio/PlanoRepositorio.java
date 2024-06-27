package com.example.hospitalpd.api.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.example.hospitalpd.api.modelo.PlanoDeSaude;

public interface PlanoRepositorio extends CrudRepository<PlanoDeSaude, Long> {

}
