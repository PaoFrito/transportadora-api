package br.com.uniamerica.transportadora.transportadoraapi.service;

import br.com.uniamerica.transportadora.transportadoraapi.entity.HistoricoFrete;
import br.com.uniamerica.transportadora.transportadoraapi.entity.Marca;
import br.com.uniamerica.transportadora.transportadoraapi.repositoty.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MarcaService {


    @Autowired
    public MarcaRepository marcaRepository;

    public Marca findById(Long id){
        return this.marcaRepository.findById(id).orElse(new Marca());
    }

    public List<Marca> findAll(){
        return this.marcaRepository.findAll();
    }

    @Transactional
    public Marca save(Marca marca){
        return this.marcaRepository.save(marca);
    }

    @Transactional
    public void atualizar(final Long id,final Marca marca){
        if (id.equals(marca.getId()) && !this.marcaRepository.findById(id).isEmpty()){
            this.marcaRepository.save(marca);
        }else{
            throw new RuntimeException("Id não encontrado");
        }
    }

    @Transactional
    public void excluir(final Long id, final Marca marca){
        if (id.equals(marca.getId()) && !this.marcaRepository.findById(id).isEmpty()){
            this.marcaRepository.delete(marca);
        }else{
            throw new RuntimeException("Id não encontrado");
        }
    }



}
