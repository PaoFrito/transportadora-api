package br.com.uniamerica.transportadora.transportadoraapi.controller;

import br.com.uniamerica.transportadora.transportadoraapi.entity.TipoDespesa;
import br.com.uniamerica.transportadora.transportadoraapi.repositoty.TipoDespesaRepository;
import br.com.uniamerica.transportadora.transportadoraapi.service.TipoDespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/api/tipodespesas")
public class TipoDespesaController {


    @Autowired
    public TipoDespesaRepository tipoDespesaRepository;

    @Autowired
    public TipoDespesaService tipoDespesaService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(this.tipoDespesaRepository.findByAtivoTrue());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoDespesa> findById(
            @RequestParam final Long id
    ){
        return ResponseEntity.ok().body(this.tipoDespesaRepository.findById(id).orElse(new TipoDespesa()));
    }


    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody TipoDespesa tipoDespesa){
        this.tipoDespesaRepository.save(tipoDespesa);
        return ResponseEntity.ok().body("Registro cadastrado com sucesso");
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable final Long id,
            @RequestBody TipoDespesa tipoDespesa
    ){
        try {
            this.tipoDespesaService.atualizar(id,tipoDespesa);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body("Registro atualizado com sucesso");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(
            @PathVariable final Long id,
            @RequestBody TipoDespesa tipoDespesa
    ){
        try {
            this.tipoDespesaService.excluir(id,tipoDespesa);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body("Registro deletado com sucesso");
    }
}
