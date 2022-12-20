package br.com.uniamerica.transportadora.transportadoraapi.controller;

import br.com.uniamerica.transportadora.transportadoraapi.entity.Caminhao;

import br.com.uniamerica.transportadora.transportadoraapi.repositoty.CaminhaoRepository;
import br.com.uniamerica.transportadora.transportadoraapi.service.CaminhaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/caminhoes")
public class CaminhaoController {

    @Autowired
    private CaminhaoRepository caminhaoRepository;

    @Autowired
    public CaminhaoService caminhaoService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(this.caminhaoRepository.findByCaminhoesAtivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Caminhao> findById(
            @RequestParam("id") final Long id
    ) {
        return ResponseEntity.ok().body(this.caminhaoRepository.findById(id).orElse(new Caminhao()));
    }


    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final Caminhao caminhao){
        this.caminhaoRepository.save(caminhao);
        return ResponseEntity.ok().body("Registro cadastrado com sucesso");
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable final Long id,
            @RequestBody final Caminhao caminhao
    ) {
        try {
            this.caminhaoService.atualizar(id,caminhao);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body("Registro atualizado com sucesso");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(
            @PathVariable final Long id,
            @RequestBody final Caminhao caminhao
    ) {
        try {
            this.caminhaoService.excluir(id,caminhao);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body("Registro deletado com sucesso");
    }


}




