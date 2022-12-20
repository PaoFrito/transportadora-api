package br.com.uniamerica.transportadora.transportadoraapi.repositoty;

import br.com.uniamerica.transportadora.transportadoraapi.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface FreteRepository extends JpaRepository<Frete, Long> {

    @Query(value = "select * from transportadora.td_fretes where id = :id", nativeQuery = true)
    public List<Frete> findById(@Param("id") final long id);

    @Query("from Frete where ativo = :ativo")
    public List<Frete> findByAtivoTrue();

    @Query("from Frete where statusFrete = :statusFrete")
    public List<Frete> findByStatusFrete(@Param("statusFrete") StatusFrete statusFrete);

    @Query("from Frete where produto = :produto")
    public List<Frete> findByProduto(@Param("produto") Produto produto);

    @Query("from Frete where motorista = :motorista")
    public List<Frete> findByMotorista(@Param("motorista") Usuario motorista);

    @Query("from Frete where cidadeOrigem = :cidadeOrigem")
    public List<Frete> findByCidadeOrigem(@Param("cidadeOrigem") Cidade cidadeOrigem);

    @Query("from Frete where cidadeDestino = :cidadeDestino")
    public List<Frete> findByCidadeDestino(@Param("cidadeDestino") Cidade cidadeDestino);

    @Query("from Frete where caminhao = :caminhao")
    public List<Frete> findByCaminhao(@Param("caminhao") Caminhao caminhao);

    @Query("from Frete where quilometragemIni = :quilometragemIni")
    public List<Frete> findByQuilometragemIni(@Param("quilometragemIni") int quilometragemIni);

    @Query("from Frete where quilometragemFim = :quilometragemFim")
    public List<Frete> findByQuilometragemFim(@Param("quilometragemFim") int quilometragemFim);

    @Query("from Frete where totalBrutoRecebidoNota = :totalBrutoRecebidoNota")
    public List<Frete> findByTotalBrutoRecebidoNota(@Param("totalBrutoRecebidoNota") BigDecimal totalBrutoRecebidoNota);

    @Query("from Frete where totalLiquidoRecebido = :totalLiquidoRecebido")
    public List<Frete> findByTotalLiquidoRecebido(@Param("totalLiquidoRecebido") BigDecimal totalLiquidoRecebido);

    @Query("from Frete where pesoInicial = :pesoInicial")
    public List<Frete> findByPesoInicial(@Param("pesoInicial") BigDecimal pesoInicial);

    @Query("from Frete where pesoFinal = :pesoFinal")
    public List<Frete> findByPesoFinal(@Param("pesoFinal") BigDecimal pesoFinal);

    @Query("from Frete where pesoFinalTransportado = :pesoFinalTransportado")
    public List<Frete> findByPesoFinalTransportado(@Param("pesoFinalTransportado") BigDecimal pesoFinalTransportado);

    @Query("from Frete where precoTonelada = :precoTonelada")
    public List<Frete> findByPrecoTonelada(@Param("precoTonelada") BigDecimal precoTonelada);

    @Query("from Frete where observacao = :observacao")
    public List<Frete> findByObservacao(@Param("observacao") String observacao);

    @Query("from Frete where statusFrete <> :cancelado and statusFrete <> :faturado")
    List<Frete> getAllInProgress(@Param("cancelado") final StatusFrete cancelado, @Param("faturado") final StatusFrete faturado);
}
