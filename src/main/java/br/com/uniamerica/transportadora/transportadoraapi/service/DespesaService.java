package br.com.uniamerica.transportadora.transportadoraapi.service;

import br.com.uniamerica.transportadora.transportadoraapi.entity.Despesa;
import br.com.uniamerica.transportadora.transportadoraapi.entity.Grupo;
import br.com.uniamerica.transportadora.transportadoraapi.entity.Usuario;
import br.com.uniamerica.transportadora.transportadoraapi.repositoty.DespesaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DespesaService {


    @Autowired
    public DespesaRepository despesaRepository;
    @Autowired
    public UsuarioService usuarioService;


    public Despesa findById(Long id) throws Exception {
        return this.despesaRepository.findById(id).orElseThrow(() -> new RuntimeException("Não encontrado"));
    }

    public List<Despesa> findAll(){
        return this.despesaRepository.findAll();
    }


    @Transactional
    public Despesa save(Despesa despesa){
       return this.despesaRepository.save(despesa);
    }

    @Transactional
    public void atualizar(final Long id,final Despesa despesa){
        if (id.equals(despesa.getId()) && !this.despesaRepository.findById(id).isEmpty()){
            this.despesaRepository.save(despesa);
        }else{
            throw new RuntimeException("Id não encontrado");
        }
    }

    @Transactional
    public void aprovar(final Long id,final Long userId) throws Exception {
        Despesa despesa = this.despesaRepository.findById(id).orElseThrow(()-> new Exception("Despesa nao encontrada"));
        Usuario user = this.usuarioService.findById(userId);
        if (id.equals(despesa.getId()) && user.getGrupo() == Grupo.ADMINISTRADOR){
            despesa.setAprovador(user);
            this.despesaRepository.save(despesa);
        }else{
            throw new RuntimeException("Invalido");
        }
    }

    @Transactional
    public void excluir(final Long id, final Despesa despesa){
        if (id.equals(despesa.getId()) && !this.despesaRepository.findById(id).isEmpty()){
            this.despesaRepository.delete(despesa);
        }else{
            throw new RuntimeException("Id não encontrado");
        }
    }



}
