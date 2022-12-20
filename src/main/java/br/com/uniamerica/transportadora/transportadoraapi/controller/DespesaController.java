package br.com.uniamerica.transportadora.transportadoraapi.controller;

import br.com.uniamerica.transportadora.transportadoraapi.entity.Despesa;
import br.com.uniamerica.transportadora.transportadoraapi.entity.Usuario;
import br.com.uniamerica.transportadora.transportadoraapi.repositoty.DespesaRepository;
import br.com.uniamerica.transportadora.transportadoraapi.service.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/api/despesas")
@CrossOrigin( origins = "*", allowedHeaders = "*")
public class DespesaController {


    @Autowired
    public DespesaRepository despesaRepository;

    @Autowired
    public DespesaService despesaService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(this.despesaRepository.findByAtivoTrue());
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") final Long id){
        try{
            return ResponseEntity.ok().body(this.despesaService.findById(id));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/despesa/{aprovadorId}")
    public ResponseEntity<?> findByAprovador(@PathVariable("aprovadorId") Long aprovadorId){
        return ResponseEntity.ok().body(this.despesaRepository.findByAprovadorNull(aprovadorId));
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final Despesa despesa){

        this.despesaRepository.save(despesa);
        return ResponseEntity.ok().body("Registro cadastrado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable final Long id,@RequestBody Despesa despesa){
       try {
           this.despesaService.atualizar(id,despesa);
           return ResponseEntity.ok().body("Registro atualizado com sucesso");
       }catch (Exception e){
           return ResponseEntity.badRequest().body(e.getMessage());
       }
    }

    @PutMapping("/aprovar/{id}")
    public ResponseEntity<?> aprovar(@PathVariable final Long id, @RequestBody Long userId){
        try {
            this.despesaService.aprovar(id, userId);
            return ResponseEntity.ok().body("Registro atualizado com sucesso");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
     public ResponseEntity<?> excluir(
             @PathVariable Long id,
             @RequestBody Despesa despesa
    ){
        try {
            this.despesaService.excluir(id,despesa);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body("Registro deletado com sucesso");
    }

}
