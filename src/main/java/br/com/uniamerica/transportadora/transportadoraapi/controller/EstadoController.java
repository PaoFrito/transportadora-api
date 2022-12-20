package br.com.uniamerica.transportadora.transportadoraapi.controller;

import br.com.uniamerica.transportadora.transportadoraapi.entity.Estado;
import br.com.uniamerica.transportadora.transportadoraapi.repositoty.EstadoRepository;
import br.com.uniamerica.transportadora.transportadoraapi.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/api/estados")
public class EstadoController {

    @Autowired
    public EstadoRepository estadoRepository;

    @Autowired
    public EstadoService estadoService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(this.estadoRepository.findByEstadosAtivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estado> findById(
            @RequestParam final Long id
    ){
        return ResponseEntity.ok().body(this.estadoRepository.findById(id).orElse(new Estado()));
    }


    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final Estado estado){
        this.estadoRepository.save(estado);
        return ResponseEntity.ok().body("Registro cadastrado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable Long id,
            @RequestBody final Estado estado
    ){
       try {
           this.estadoService.atualizar(id,estado);
       }catch (Exception e){
           return ResponseEntity.badRequest().body(e.getMessage());
       }
        return ResponseEntity.ok().body("Registro atualizado com sucesso");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(
            @PathVariable Long id,
            @RequestBody Estado estado
    ){
       try {
           this.estadoService.excluir(id,estado);
       }catch (Exception e){
           return ResponseEntity.badRequest().body(e.getMessage());
       }
        return ResponseEntity.ok().body("Registro deletado com sucesso");
    }
}
