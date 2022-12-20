package br.com.uniamerica.transportadora.transportadoraapi.controller;

import br.com.uniamerica.transportadora.transportadoraapi.entity.Grupo;
import br.com.uniamerica.transportadora.transportadoraapi.entity.Usuario;
import br.com.uniamerica.transportadora.transportadoraapi.repositoty.UsuarioRepository;
import br.com.uniamerica.transportadora.transportadoraapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    public UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(this.usuarioRepository.findByUsuariosAtivos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable final Long id){
        return ResponseEntity.ok().body(this.usuarioRepository.findById(id).orElse(new Usuario()));
    }

    @GetMapping("/grupo/{grupo}")
    public ResponseEntity<?> GetByGroup(@PathVariable final Grupo grupo){
        try{
            List<Usuario> res = this.usuarioRepository.findByGrupo(grupo);

            return ResponseEntity.ok().body(res);
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(
            @RequestBody final Usuario usuario
    ){
        this.usuarioRepository.save(usuario);
        return ResponseEntity.ok().body("Registro cadastrado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable final Long id,
            @RequestBody Usuario usuario
    ){
     try{
         this.usuarioService.atualizar(id,usuario);
     }catch (Exception e){
         return ResponseEntity.badRequest().body(e.getMessage());
     }
        return ResponseEntity.ok().body("Registro atualizado com sucesso");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(
            @PathVariable final Long id,
            @RequestBody Usuario usuario
    ){
        try {
            this.usuarioService.excluir(id,usuario);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body("Registro deletado com sucesso");
    }
}
