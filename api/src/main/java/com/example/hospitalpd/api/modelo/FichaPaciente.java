package com.example.hospitalpd.api.modelo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "FichaPaciente")
@Getter
@Setter
public class FichaPaciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ficha_id", nullable = false)
    private long id;

    @Column(name = "nomePaciente", nullable = false)
    private String nome;
    private String nmCarteiraPlano;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "especialidade_id", referencedColumnName = "id")
    private Especialidade especialidade;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "plano_id", referencedColumnName = "id")
    private PlanoDeSaude planoDeSaude;

    public void cadastraPlanoEEspecialidade(Especialidade especialidade2, PlanoDeSaude planoDeSaude2) {
        this.especialidade = especialidade2;
        this.planoDeSaude = planoDeSaude2;
    }
}
