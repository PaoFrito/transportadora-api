package br.com.uniamerica.transportadora.transportadoraapi.controller;

import br.com.uniamerica.transportadora.transportadoraapi.dto.frete.*;
import br.com.uniamerica.transportadora.transportadoraapi.entity.Frete;
import br.com.uniamerica.transportadora.transportadoraapi.repositoty.FreteRepository;
import br.com.uniamerica.transportadora.transportadoraapi.service.FreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/fretes")
@CrossOrigin( origins = "*", allowedHeaders = "*")
public class FreteController {

    @Autowired
    public FreteRepository freteRepository;

    @Autowired
    public FreteService freteService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(this.freteRepository.findAll(Sort.by("atualizado").descending()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Frete> findById(@PathVariable final Long id){
        return ResponseEntity.ok().body(this.freteRepository.findById(id).orElse(new Frete()));
    }

    @GetMapping("/inProgress")
    public ResponseEntity<?> getAllInProgress(){
        try{
            return ResponseEntity.ok().body(this.freteService.getAllInProgress());
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final NewFreteDTO newFrete){

        this.freteService.save(newFrete);

        return ResponseEntity.ok().body("Registro cadastrado com sucesso");
    }

    @PutMapping("/status/em_transporte/{id}")
    public ResponseEntity<?> putFrete(@PathVariable final Long id, @RequestBody FreteEmTransporte frete){
        try {
            this.freteService.putStatusEmTransporte(id,frete);
            return ResponseEntity.ok().body("Registro atualizado com sucesso");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/status/interrompido/{id}")
    public ResponseEntity<?> putStatusInterrompido(@PathVariable final Long id,@RequestBody FreteObservacao frete){
        try {
            this.freteService.putStatusInterrompido(id,frete);
            return ResponseEntity.ok().body("Registro atualizado com sucesso");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/status/descarga/{id}")
    public ResponseEntity<?> putStatusDescarga(@PathVariable final Long id,@RequestBody FreteDescarga frete){
        try {
            this.freteService.putStatusDescarga(id,frete);
            return ResponseEntity.ok().body("Registro atualizado com sucesso");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/status/faturado/{id}")
    public ResponseEntity<?> putStatusFaturado(@PathVariable final Long id,@RequestBody FreteAualizado frete){
        try{
            this.freteService.putStatusFaturado(id,frete);
            return ResponseEntity.ok().body("Registro atualizado com sucesso");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/status/cancelado/{id}")
    public ResponseEntity<?> putStatusCancelado( @PathVariable final Long id, @RequestBody FreteObservacao frete){
        try {
            this.freteService.putStatusCancelado(id,frete);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body("Registro atualizado com sucesso");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable final Long id,@RequestBody Frete frete){
      try {
          this.freteService.excluir(id,frete);
          return ResponseEntity.ok().body("Registro deletado com sucesso");
      }catch (Exception e){
          return ResponseEntity.badRequest().body(e.getMessage());
      }
    }
}


