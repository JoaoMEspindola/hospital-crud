package com.example.hospitalpd.api.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.hospitalpd.api.modelo.Especialidade;
import com.example.hospitalpd.api.modelo.FichaPaciente;
import com.example.hospitalpd.api.modelo.PlanoDeSaude;
import com.example.hospitalpd.api.repositorio.EspecialidadeRepositorio;
import com.example.hospitalpd.api.repositorio.PlanoRepositorio;
import com.example.hospitalpd.api.repositorio.Repositorio;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = "*")
public class Controle {

    @Autowired
    private Repositorio fichaPacienteRepositorio;

    @Autowired
    private EspecialidadeRepositorio especialidadeRepositorio;

    @Autowired
    private PlanoRepositorio planoRepositorio;

    @GetMapping("/")
    public Iterable<FichaPaciente> selecionar() {
        return fichaPacienteRepositorio.findAll();
    }

    @GetMapping("/fichas/especialidade/{especialidadeId}")
    public ResponseEntity<?> buscarFichasComEspecialidade(
            @PathVariable Long especialidadeId) {

        Especialidade especialidade = especialidadeRepositorio.findById(especialidadeId).get();

        List<FichaPaciente> fichasEspecializadas = fichaPacienteRepositorio.findByEspecialidade(especialidade);

        if (fichasEspecializadas.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("Não há fichas cadastradas com a especialidade " + especialidade.getNome());
        }

        return ResponseEntity.ok(fichasEspecializadas);
    }

    @GetMapping("/fichas/plano/{planoId}")
    public ResponseEntity<?> buscarFichasComPlano(
            @PathVariable Long planoId) {

        PlanoDeSaude planoDeSaude = planoRepositorio.findById(planoId).get();

        List<FichaPaciente> fichasDoPlano = fichaPacienteRepositorio.findByPlanoDeSaude(planoDeSaude);

        if (fichasDoPlano.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("Não há fichas cadastradas com o plano " + planoDeSaude.getNome());
        }

        return ResponseEntity.ok(fichasDoPlano);
    }

    @PutMapping("/{fichaId}/especialidade/{especialidadeId}/planoDeSaude/{planoId}")
    public ResponseEntity<?> editar(
            @RequestBody FichaPaciente ficha,
            @PathVariable Long especialidadeId,
            @PathVariable Long planoId) {

        Especialidade especialidade = especialidadeRepositorio.findById(especialidadeId).get();
        PlanoDeSaude planoDeSaude = planoRepositorio.findById(planoId).get();

        FichaPaciente fichaProibida = fichaPacienteRepositorio.findByNmCarteiraPlanoAndEspecialidadeAndPlanoDeSaude(
                ficha.getNmCarteiraPlano(), especialidade, planoDeSaude);

        if (fichaProibida != null) {
            return ResponseEntity.badRequest().body("Paciente da carteira " + ficha.getNmCarteiraPlano()
                    + " ja possui consulta no plano " + planoDeSaude.getNome() + " com a especialidade "
                    + especialidade.getNome());
        }

        ficha.cadastraPlanoEEspecialidade(especialidade, planoDeSaude);
        FichaPaciente fichaSalva = fichaPacienteRepositorio.save(ficha);
        return ResponseEntity.ok(fichaSalva);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable long id) {
        FichaPaciente fichaPaciente = fichaPacienteRepositorio.findById(id).get();
        fichaPaciente.setEspecialidade(null);
        fichaPaciente.setPlanoDeSaude(null);
        fichaPacienteRepositorio.deleteById(id);
    }

    @PostMapping("/{fichaId}/especialidade/{especialidadeId}/planoDeSaude/{planoId}")
    public ResponseEntity<?> cadastrar(
            @RequestBody FichaPaciente ficha,
            @PathVariable Long especialidadeId,
            @PathVariable Long planoId) {

        Especialidade especialidade = especialidadeRepositorio.findById(especialidadeId).get();
        PlanoDeSaude planoDeSaude = planoRepositorio.findById(planoId).get();

        FichaPaciente fichaProibida = fichaPacienteRepositorio.findByNmCarteiraPlanoAndEspecialidadeAndPlanoDeSaude(
                ficha.getNmCarteiraPlano(), especialidade, planoDeSaude);

        if (fichaProibida != null) {
            return ResponseEntity.badRequest().body("Paciente da carteira " + ficha.getNmCarteiraPlano()
                    + " ja possui consulta no plano " + planoDeSaude.getNome() + " com a especialidade "
                    + especialidade.getNome());
        }

        ficha.cadastraPlanoEEspecialidade(especialidade, planoDeSaude);
        FichaPaciente fichaSalva = fichaPacienteRepositorio.save(ficha);
        return ResponseEntity.ok(fichaSalva);
    }

}
