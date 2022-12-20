package br.com.uniamerica.transportadora.transportadoraapi.repositoty;

import br.com.uniamerica.transportadora.transportadoraapi.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DespesaRepository extends JpaRepository <Despesa, Long>{

    @Query("from Despesa where id = :id")
    public List<Despesa> findById();

    @Query("from Despesa where ativo is true")
    public List<Despesa> findByAtivoTrue();

    @Query("from Despesa where tipoDespesa = :tipoDespesa")
    public List<Despesa> findByTipoDespesa(@Param("tipoDespesa") TipoDespesa tipoDespesa);

    @Query("from Despesa where valor = :valor")
    public List<Despesa> findByValor(@Param("valor") BigDecimal valor);

    @Query("from Despesa where motorista = :motorista")
    public List<Despesa> findByMotorista(@Param("motorista") Usuario motorista);

    @Query("from Despesa where data = :data")
    public List<Despesa> findByData(@Param("data") LocalDateTime data);

    @Query("from Despesa despesa where despesa.aprovador.id = :aprovadorId")
    public List<Despesa> findByAprovadorNull(@Param("aprovadorId") Long aprovadorId);

    @Query("from Despesa where frete = :frete")
    public List<Despesa> findByFrete(@Param("frete") Frete frete);

}
