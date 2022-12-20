package br.com.uniamerica.transportadora.transportadoraapi.service;

import br.com.uniamerica.transportadora.transportadoraapi.entity.Cidade;
import br.com.uniamerica.transportadora.transportadoraapi.repositoty.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CidadeService {

    @Autowired
    public CidadeRepository cidadeRepository;

    public Cidade findById(Long id){
      return  this.cidadeRepository.findById(id).orElse(new Cidade());
    }

    public List<Cidade> findAll(){
        return this.cidadeRepository.findAll();
    }


    @Transactional
    public Cidade save(Cidade cidade){
        return this.cidadeRepository.save(cidade);
    }


    @Transactional
    public void atualizar(final Long id,final Cidade cidade){
        if (id.equals(cidade.getId()) && !this.cidadeRepository.findById(id).isEmpty()){
            this.cidadeRepository.save(cidade);
        }else{
            throw new RuntimeException("Id não encontrado");
        }
    }

    @Transactional
    public void excluir(final Long id, final Cidade cidade){
        if (id.equals(cidade.getId()) && !this.cidadeRepository.findById(id).isEmpty()){
            this.cidadeRepository.delete(cidade);
        }else{
            throw new RuntimeException("Id não encontrado");
        }
    }







}
