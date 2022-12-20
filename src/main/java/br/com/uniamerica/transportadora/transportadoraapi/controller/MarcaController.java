package br.com.uniamerica.transportadora.transportadoraapi.controller;

import br.com.uniamerica.transportadora.transportadoraapi.entity.Marca;
import br.com.uniamerica.transportadora.transportadoraapi.repositoty.MarcaRepository;

import br.com.uniamerica.transportadora.transportadoraapi.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/api/marcas")
public class MarcaController {

    @Autowired
    public MarcaRepository marcaRepository;

    @Autowired
    public MarcaService marcaService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(this.marcaRepository.findByAtivoTrue());
    }

    @GetMapping("/{id}")
     public ResponseEntity<Marca> findById(
             @RequestParam("id") final Long id
    ){
        return ResponseEntity.ok().body(this.marcaRepository.findById(id).orElse(new Marca()));
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final Marca marca){
        this.marcaRepository.save(marca);
        return ResponseEntity.ok().body("Registro cadastrado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable final Long id,
            @RequestBody Marca marca
    ){
        try {
            this.marcaService.atualizar(id,marca);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body("Registro atualizado com sucesso");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(
            @PathVariable Long id,
            @RequestBody Marca marca
    ){
        try {
            this.marcaService.excluir(id,marca);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body("Registro deletado com sucesso");
    }
}
