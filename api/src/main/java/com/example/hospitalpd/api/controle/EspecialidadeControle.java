package com.example.hospitalpd.api.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.hospitalpd.api.modelo.Especialidade;
import com.example.hospitalpd.api.repositorio.EspecialidadeRepositorio;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = "*")
public class EspecialidadeControle {

    @Autowired
    private EspecialidadeRepositorio especialidade;

    @PostMapping("/especialidades")
    public Especialidade cadastrar(@RequestBody Especialidade e) {
        return especialidade.save(e);
    }

    @GetMapping("/especialidades")
    public Iterable<Especialidade> selecionar() {
        return especialidade.findAll();
    }

    @PutMapping("/especialidades")
    public Especialidade editar(@RequestBody Especialidade e) {
        return especialidade.save(e);
    }

    @DeleteMapping("/especialidades/{id}")
    public void remover(@PathVariable long id) {
        especialidade.deleteById(id);
    }
}
