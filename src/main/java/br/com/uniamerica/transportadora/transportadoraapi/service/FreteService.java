package br.com.uniamerica.transportadora.transportadoraapi.service;

import br.com.uniamerica.transportadora.transportadoraapi.dto.frete.*;
import br.com.uniamerica.transportadora.transportadoraapi.entity.Despesa;
import br.com.uniamerica.transportadora.transportadoraapi.entity.Frete;
import br.com.uniamerica.transportadora.transportadoraapi.entity.StatusFrete;
import br.com.uniamerica.transportadora.transportadoraapi.entity.Usuario;
import br.com.uniamerica.transportadora.transportadoraapi.repositoty.DespesaRepository;
import br.com.uniamerica.transportadora.transportadoraapi.repositoty.FreteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class FreteService {

    @Autowired
    public FreteRepository freteRepository;

    @Autowired
    public HistoricoFreteService historicoFreteService;

    @Autowired
    public DespesaRepository despesaRepository;

    public List<Frete> getAllInProgress() throws ChangeSetPersister.NotFoundException {
        List<Frete> listaFretes = this.freteRepository.getAllInProgress(StatusFrete.CANCELADO, StatusFrete.FATURADO);

        if(listaFretes == null)
            throw new ChangeSetPersister.NotFoundException();

        return listaFretes;
    }

    public Frete findById(Long id){
        return this.freteRepository.findById(id).orElse(new Frete());
    }

    public List<Frete> findByAll(){
        return this.freteRepository.findAll();
    }

    @Transactional
    public void save(NewFreteDTO newFrete){

        if(
            newFrete.getCidadeOrigem() == null || newFrete.getCidadeDestino() == null || newFrete.getMotorista() == null ||
            newFrete.getCaminhao() == null || newFrete.getProduto() == null || newFrete.getPrecoTonelada() == null || newFrete.getExecutor() == null
        )
            throw new RuntimeException("Um ou mais campos invalidos");

        Frete frete = new Frete();

        frete.setCidadeOrigem(newFrete.getCidadeOrigem());
        frete.setCidadeDestino(newFrete.getCidadeDestino());
        frete.setMotorista(newFrete.getMotorista());
        frete.setCaminhao(newFrete.getCaminhao());
        frete.setProduto(newFrete.getProduto());
        frete.setPrecoTonelada(newFrete.getPrecoTonelada());
        frete.setStatusFrete(StatusFrete.CARGA);

        this.updateStatus(frete, newFrete.getExecutor());
    }

    public void putStatusEmTransporte(final Long id,final FreteEmTransporte newFrete) throws Exception {

        Frete frete = this.isUpdateValid(id, newFrete.getId());

        if (frete.getStatusFrete() != StatusFrete.CARGA)
            throw new Exception("Invalido");

        frete.setQuilometragemIni(newFrete.getQuilometragemIni());
        frete.setPesoInicial(newFrete.getPesoInicial());
        frete.setStatusFrete(StatusFrete.EM_TRANSPORTE);

        this.updateStatus(frete, newFrete.getExecutor());
    }

    public void putStatusInterrompido(final Long id, final FreteObservacao newFrete) throws Exception {

        Frete frete = this.isUpdateValid(id, newFrete.getId());

        if (!(frete.getStatusFrete() == StatusFrete.CARGA || frete.getStatusFrete() == StatusFrete.EM_TRANSPORTE))
            throw new Exception("Invalido");

        frete.setObservacao(newFrete.getObservacao());
        frete.setStatusFrete(StatusFrete.INTERROMPIDO);

        this.updateStatus(frete, newFrete.getExecutor());
    }

    public void putStatusDescarga(final Long id, final FreteDescarga newFrete) throws Exception {

        Frete frete = this.isUpdateValid(id, newFrete.getId());

        if (!(frete.getStatusFrete() == StatusFrete.EM_TRANSPORTE || frete.getStatusFrete() == StatusFrete.INTERROMPIDO))
            throw new Exception("Invalido");

        frete.setQuilometragemFim(newFrete.getQuilometragemFim());
        frete.setPesoFinal(newFrete.getPesoFinal());
        frete.setStatusFrete(StatusFrete.DESCARGA);

        this.updateStatus(frete, newFrete.getExecutor());
    }

    @Transactional @Modifying
    public void putStatusFaturado(final Long id, final FreteAualizado newFrete) throws Exception {

        Frete frete = this.isUpdateValid(id, newFrete.getId());

        if (!(frete.getStatusFrete() == StatusFrete.DESCARGA))
            throw new Exception("Invalido");

        final List<Despesa> despesas = this.despesaRepository.findByAprovadorNull(newFrete.getId());

        if(despesas.size() > 0)
            throw new Exception("Frete com despesas em aberto, não é possivel faturalo");

        frete.setStatusFrete(StatusFrete.FATURADO);

        this.updateStatus(frete, newFrete.getExecutor());
    }

    @Transactional @Modifying
    public void putStatusCancelado(final Long id, final FreteObservacao newFrete) throws Exception {

        Frete frete = this.isUpdateValid(id, newFrete.getId());

        if (!(frete.getStatusFrete() == StatusFrete.CARGA ||frete.getStatusFrete() == StatusFrete.EM_TRANSPORTE || frete.getStatusFrete() == StatusFrete.INTERROMPIDO))
            throw new Exception("Invalido");

        frete.setObservacao(newFrete.getObservacao());
        frete.setStatusFrete(StatusFrete.CANCELADO);

        this.updateStatus(frete, newFrete.getExecutor());
    }

    @Transactional @Modifying
    public void excluir(final Long id, final Frete frete){
        if (id.equals(frete.getId()) && !this.freteRepository.findById(id).isEmpty()){
            this.freteRepository.delete(frete);
        }else{
            throw new RuntimeException("Id não encontrado");
        }
    }

    private Frete isUpdateValid(final Long id, final Long freteId) throws ChangeSetPersister.NotFoundException {

        if (!id.equals(freteId))
            throw new RuntimeException("id invalido");

        return this.freteRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);

    }

    @Transactional @Modifying
    public void updateStatus(final Frete frete, final Usuario executor){
        Frete updatedFrete = this.freteRepository.save(frete);
        this.historicoFreteService.cadastrar(updatedFrete, executor);
    }
}
