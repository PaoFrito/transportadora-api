package br.com.uniamerica.transportadora.transportadoraapi.controller;

import br.com.uniamerica.transportadora.transportadoraapi.entity.Modelo;
import br.com.uniamerica.transportadora.transportadoraapi.repositoty.ModeloRepository;
import br.com.uniamerica.transportadora.transportadoraapi.service.ModeloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/api/modelos")
public class ModeloController {

    @Autowired
    public ModeloRepository modeloRepository;

    @Autowired
    public ModeloService modeloService;


    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(this.modeloRepository.findByAtivoTrue());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Modelo> findById(
            @RequestParam final Long id
    ){
        return ResponseEntity.ok().body(this.modeloRepository.findById(id).orElse(new Modelo()));
    }

    @GetMapping("/modelo/{marcaId}")
     public ResponseEntity<?> finByMarca(@PathVariable("marcaId") Long marcaId){
        return ResponseEntity.ok().body(this.modeloRepository.findByMarca(marcaId));
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Modelo modelo){
        this.modeloRepository.save(modelo);
        return ResponseEntity.ok().body("Registro cadastrado com sucesso");
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable final Long id,
            @RequestBody Modelo modelo
    ){
        try {
            this.modeloService.atualizar(id,modelo);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body("Registro atualizado com sucesso");
    }

   @DeleteMapping("/{id}")
   public ResponseEntity<?> excluir(
           @PathVariable final Long id,
           @RequestBody Modelo modelo
   ){
       try {
           this.modeloService.excluir(id,modelo);
       }catch (Exception e){
           return ResponseEntity.badRequest().body(e.getMessage());
       }
        return ResponseEntity.ok().body("Registro deletado com sucesso");
   }
}





