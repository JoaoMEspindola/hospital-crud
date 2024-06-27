package com.example.hospitalpd.api.repositorio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.hospitalpd.api.modelo.Especialidade;
import com.example.hospitalpd.api.modelo.FichaPaciente;
import com.example.hospitalpd.api.modelo.PlanoDeSaude;

@Repository
public interface Repositorio extends CrudRepository<FichaPaciente, Long> {
    FichaPaciente findByNmCarteiraPlanoAndEspecialidadeAndPlanoDeSaude(
            String nmCarteiraPlano, Especialidade especialidade, PlanoDeSaude planoDeSaude);

    List<FichaPaciente> findByEspecialidade(Especialidade especialidade);

    List<FichaPaciente> findByPlanoDeSaude(PlanoDeSaude planoDeSaude);
}
