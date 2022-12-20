package br.com.uniamerica.transportadora.transportadoraapi.controller;

import br.com.uniamerica.transportadora.transportadoraapi.entity.Cidade;
import br.com.uniamerica.transportadora.transportadoraapi.repositoty.CidadeRepository;
import br.com.uniamerica.transportadora.transportadoraapi.service.CaminhaoService;
import br.com.uniamerica.transportadora.transportadoraapi.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    public CidadeService cidadeService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(this.cidadeRepository.findByCidadesAtivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cidade> findById(
            @RequestParam("id") final Long id
    ){
        return ResponseEntity.ok().body(this.cidadeRepository.findById(id).orElse(new Cidade()));
    }

    @GetMapping("/estado/{estadoId}")
    public ResponseEntity<?> findByEstado(@PathVariable("estadoId") Long estadoId){
        return ResponseEntity.ok().body(this.cidadeRepository.findByEstado(estadoId));
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final Cidade cidade){
        this.cidadeRepository.save(cidade);
        return ResponseEntity.ok().body("Registro cadastrado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable Long id,
            @RequestBody final Cidade cidade
    ){
        try {
            this.cidadeService.atualizar(id,cidade);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body("Registro atualizado com sucesso");
    }


    @DeleteMapping("/{id}")
     public ResponseEntity<?> excluir(
             @PathVariable final Long id,
             @RequestBody final Cidade cidade
    ){
        try {
            this.cidadeService.excluir(id,cidade);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body("Registro deletado com sucesso");
    }
}

