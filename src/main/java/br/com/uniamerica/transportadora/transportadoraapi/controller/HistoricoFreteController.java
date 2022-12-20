package br.com.uniamerica.transportadora.transportadoraapi.controller;

import br.com.uniamerica.transportadora.transportadoraapi.entity.HistoricoFrete;
import br.com.uniamerica.transportadora.transportadoraapi.repositoty.HistoricoFreteRepository;
import br.com.uniamerica.transportadora.transportadoraapi.service.HistoricoFreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/api/historicofrete")
public class HistoricoFreteController {


    @Autowired
    public HistoricoFreteRepository historicoFreteRepository;

    @Autowired
    public HistoricoFreteService historicoFreteService;


    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(this.historicoFreteRepository.findByAtivoTrue());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoricoFrete> findById(
            @RequestParam final Long id
    ){
        return ResponseEntity.ok().body(this.historicoFreteRepository.findById(id).orElse(new HistoricoFrete()));
    }

    @GetMapping("/frete/{freteId}")
    public ResponseEntity<?> findByFrete(@PathVariable("freteId") Long freteId){
        return ResponseEntity.ok().body(this.historicoFreteRepository.findByFrete(freteId));
    }


    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final HistoricoFrete historicoFrete){
        this.historicoFreteRepository.save(historicoFrete);
        return ResponseEntity.ok().body("Registro cadastrado co, sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable final Long id,
            @RequestBody HistoricoFrete historicoFrete
    ){
       try {
           this.historicoFreteService.atualizar(id,historicoFrete);
       }catch (Exception e){
           return ResponseEntity.badRequest().body(e.getMessage());
       }
        return ResponseEntity.ok().body("Registro atualizado com sucesso");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(
            @PathVariable Long id,
            @RequestBody HistoricoFrete historicoFrete
    ){
        try {
            this.historicoFreteService.excluir(id,historicoFrete);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return  ResponseEntity.ok().body("Registro deletado com sucesso");
    }


}
