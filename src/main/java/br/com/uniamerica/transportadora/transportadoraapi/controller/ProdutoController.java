package br.com.uniamerica.transportadora.transportadoraapi.controller;

import br.com.uniamerica.transportadora.transportadoraapi.entity.Produto;
import br.com.uniamerica.transportadora.transportadoraapi.repositoty.ProdutoRepository;
import br.com.uniamerica.transportadora.transportadoraapi.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/api/produtos")
public class ProdutoController {

  @Autowired
  public ProdutoRepository produtoRepository;

  @Autowired
  public ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(this.produtoRepository.findByProdutosAtivos());
    }

  @GetMapping("/{id}")
    public ResponseEntity<Produto> findById(
            @RequestParam final Long id
  ){
      return ResponseEntity.ok().body(this.produtoRepository.findById(id).orElse(new Produto()));
  }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final Produto produto){
      this.produtoRepository.save(produto);
      return ResponseEntity.ok().body("Registro cadastrado com sucesso");
  }

  @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable final Long id,
            @RequestBody Produto produto
  ){
      try {
          this.produtoService.atualizar(id,produto);
      }catch (Exception e){
          return ResponseEntity.badRequest().body(e.getMessage());
      }
      return ResponseEntity.ok().body("Registro atualizado com sucesso");
  }


  @DeleteMapping("/{id}")
   public ResponseEntity<?> excluir(
           @PathVariable final Long id,
           @RequestBody Produto produto
  ){
     try {
         this.produtoService.excluir(id,produto);
     }catch (Exception e){
         return ResponseEntity.badRequest().body(e.getMessage());
     }
      return ResponseEntity.ok().body("Registro deletado com sucesso");
  }




}
