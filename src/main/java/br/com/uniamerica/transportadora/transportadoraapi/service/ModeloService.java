package br.com.uniamerica.transportadora.transportadoraapi.service;

import br.com.uniamerica.transportadora.transportadoraapi.entity.Marca;
import br.com.uniamerica.transportadora.transportadoraapi.entity.Modelo;
import br.com.uniamerica.transportadora.transportadoraapi.repositoty.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ModeloService {


    @Autowired
    public ModeloRepository modeloRepository;

    public Modelo findById(Long id){
        return this.modeloRepository.findById(id).orElse(new Modelo());
    }

    public List<Modelo> findAll(){
        return this.modeloRepository.findAll();
    }

    @Transactional
    public Modelo save(Modelo modelo){
        return this.modeloRepository.save(modelo);
    }


    @Transactional
    public void atualizar(final Long id,final Modelo modelo){
        if (id.equals(modelo.getId()) && !this.modeloRepository.findById(id).isEmpty()){
            this.modeloRepository.save(modelo);
        }else{
            throw new RuntimeException("Id não encontrado");
        }
    }

    @Transactional
    public void excluir(final Long id, final Modelo modelo){
        if (id.equals(modelo.getId()) && !this.modeloRepository.findById(id).isEmpty()){
            this.modeloRepository.delete(modelo);
        }else{
            throw new RuntimeException("Id não encontrado");
        }
    }



}
