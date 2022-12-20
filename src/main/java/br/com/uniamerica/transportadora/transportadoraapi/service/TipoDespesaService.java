package br.com.uniamerica.transportadora.transportadoraapi.service;

import br.com.uniamerica.transportadora.transportadoraapi.entity.Produto;
import br.com.uniamerica.transportadora.transportadoraapi.entity.TipoDespesa;
import br.com.uniamerica.transportadora.transportadoraapi.repositoty.TipoDespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TipoDespesaService {


    @Autowired
    public TipoDespesaRepository tipoDespesaRepository;

    public TipoDespesa findById(Long id){
        return this.tipoDespesaRepository.findById(id).orElse(new TipoDespesa());
    }

    public List<TipoDespesa> findAll(){
        return this.tipoDespesaRepository.findAll();
    }

    @Transactional
    public TipoDespesa save(TipoDespesa tipoDespesa){
        return this.tipoDespesaRepository.save(tipoDespesa);
    }


    @Transactional
    public void atualizar(final Long id,final TipoDespesa tipoDespesa){
        if (id.equals(tipoDespesa.getId()) && !this.tipoDespesaRepository.findById(id).isEmpty()){
            this.tipoDespesaRepository.save(tipoDespesa);
        }else{
            throw new RuntimeException("Id não encontrado");
        }
    }

    @Transactional
    public void excluir(final Long id, final TipoDespesa tipoDespesa){
        if (id.equals(tipoDespesa.getId()) && !this.tipoDespesaRepository.findById(id).isEmpty()){
            this.tipoDespesaRepository.delete(tipoDespesa);
        }else{
            throw new RuntimeException("Id não encontrado");
        }
    }


}
