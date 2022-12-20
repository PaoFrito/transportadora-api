package br.com.uniamerica.transportadora.transportadoraapi.service;

import br.com.uniamerica.transportadora.transportadoraapi.entity.Usuario;
import br.com.uniamerica.transportadora.transportadoraapi.repositoty.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    public UsuarioRepository usuarioRepository;

    public Usuario findById(Long id) throws Exception {
        return this.usuarioRepository.findById(id).orElseThrow(()-> new Exception("User nao encontrado"));
    }

    public List<Usuario> findAll(){
        return this.usuarioRepository.findAll();
    }

    @Transactional
    public Usuario save(Usuario usuario){
        return this.usuarioRepository.save(usuario);
    }

    @Transactional
    public void atualizar(final Long id,final Usuario usuario) {
        if (id.equals(usuario.getId()) && !this.usuarioRepository.findById(id).isEmpty()) {
            this.usuarioRepository.save(usuario);
        } else {
            throw new RuntimeException("Id não encontrado");
        }

    }

    @Transactional
    public void excluir(final Long id, final Usuario usuario){
        if (id.equals(usuario.getId()) && !this.usuarioRepository.findById(id).isEmpty()){
            this.usuarioRepository.delete(usuario);
        }else{
            throw new RuntimeException("Id não encontrado");
        }
    }
}