package com.example.hospitalpd.api.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.hospitalpd.api.modelo.PlanoDeSaude;
import com.example.hospitalpd.api.repositorio.PlanoRepositorio;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = "*")
public class PlanoControle {

    @Autowired
    private PlanoRepositorio planoDeSaude;

    @PostMapping("/planos")
    public PlanoDeSaude cadastrar(@RequestBody PlanoDeSaude pc) {
        return planoDeSaude.save(pc);
    }

    @GetMapping("/planos")
    public Iterable<PlanoDeSaude> selecionar() {
        return planoDeSaude.findAll();
    }

    @PutMapping("/planos")
    public PlanoDeSaude editar(@RequestBody PlanoDeSaude pc) {
        return planoDeSaude.save(pc);
    }

    @DeleteMapping("/planos/{id}")
    public void remover(@PathVariable long id) {
        planoDeSaude.deleteById(id);
    }
}
