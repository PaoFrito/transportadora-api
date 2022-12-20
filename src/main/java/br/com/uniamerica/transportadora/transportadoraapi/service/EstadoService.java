package br.com.uniamerica.transportadora.transportadoraapi.service;

import br.com.uniamerica.transportadora.transportadoraapi.entity.Despesa;
import br.com.uniamerica.transportadora.transportadoraapi.entity.Estado;
import br.com.uniamerica.transportadora.transportadoraapi.repositoty.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EstadoService {



    @Autowired
    public EstadoRepository estadoRepository;

    public Estado findById(Long id){
        return this.estadoRepository.findById(id).orElse(new Estado());
    }

    public List<Estado> findAll(){
        return this.estadoRepository.findAll();
    }

    @Transactional
    public Estado save(Estado estado){
        return this.estadoRepository.save(estado);
    }

    @Transactional
    public void atualizar(final Long id,final Estado estado){
        if (id.equals(estado.getId()) && !this.estadoRepository.findById(id).isEmpty()){
            this.estadoRepository.save(estado);
        }else{
            throw new RuntimeException("Id não encontrado");
        }
    }

    @Transactional
    public void excluir(final Long id, final Estado estado){
        if (id.equals(estado.getId()) && !this.estadoRepository.findById(id).isEmpty()){
            this.estadoRepository.delete(estado);
        }else{
            throw new RuntimeException("Id não encontrado");
        }
    }








}
