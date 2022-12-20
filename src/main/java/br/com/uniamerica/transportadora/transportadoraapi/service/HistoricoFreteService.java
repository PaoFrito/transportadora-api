package br.com.uniamerica.transportadora.transportadoraapi.service;

import br.com.uniamerica.transportadora.transportadoraapi.entity.*;
import br.com.uniamerica.transportadora.transportadoraapi.repositoty.FreteRepository;
import br.com.uniamerica.transportadora.transportadoraapi.repositoty.HistoricoFreteRepository;
import br.com.uniamerica.transportadora.transportadoraapi.repositoty.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistoricoFreteService {

    @Autowired
    public HistoricoFreteRepository historicoFreteRepository;

    @Autowired
    public FreteRepository freteRepository;

    @Autowired
    public UsuarioRepository usuarioRepository;

    public HistoricoFrete findById(Long id){
        return this.historicoFreteRepository.findById(id).orElse(new HistoricoFrete());
    }

    public List<HistoricoFrete> findAll(){
        return this.historicoFreteRepository.findAll();
    }

    @Transactional
    public HistoricoFrete save(HistoricoFrete historicoFrete){
        return this.historicoFreteRepository.save(historicoFrete);
    }

    @Transactional
    public void atualizar(final Long id,final HistoricoFrete historicoFrete){
        if (id.equals(historicoFrete.getId()) && !this.historicoFreteRepository.findById(id).isEmpty()){
            this.historicoFreteRepository.save(historicoFrete);
        }else{
            throw new RuntimeException("Id não encontrado");
        }
    }

    @Transactional
    public void excluir(final Long id, final HistoricoFrete historicoFrete){
        if (id.equals(historicoFrete.getId()) && !this.historicoFreteRepository.findById(id).isEmpty()){
            this.historicoFreteRepository.delete(historicoFrete);
        }else{
            throw new RuntimeException("Id não encontrado");
        }
    }

    @Transactional
    public void cadastrar(final Frete frete, final Usuario usuario){

        final HistoricoFrete historicoFrete = new HistoricoFrete();

        historicoFrete.setStatusFrete(frete.getStatusFrete());
        historicoFrete.setFrete(frete);
        historicoFrete.setExecutor(usuario);
        this.historicoFreteRepository.save(historicoFrete);
    }





}
