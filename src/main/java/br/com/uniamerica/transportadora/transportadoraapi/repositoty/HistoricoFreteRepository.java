package br.com.uniamerica.transportadora.transportadoraapi.repositoty;

import br.com.uniamerica.transportadora.transportadoraapi.entity.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HistoricoFreteRepository extends JpaRepository<HistoricoFrete, Long> {

    @Query("from HistoricoFrete where id = :id")
    public List<HistoricoFrete> findById();

    @Query("from HistoricoFrete where ativo = :ativo")
    public List<HistoricoFrete> findByAtivoTrue();

    @Query("from HistoricoFrete historicoFrete where historicoFrete.frete.id = :freteId order by historicoFrete.cadastro ASC")
    public List<HistoricoFrete> findByFrete(@Param("freteId") Long freteId);

    @Query("from HistoricoFrete where data = :data")
    public List<HistoricoFrete> findByData(@Param("data") LocalDateTime data);

    @Query("from HistoricoFrete where statusFrete = :statusFrete")
    public List<HistoricoFrete> findByStatusFrete(@Param("statusFrete") StatusFrete statusFrete);

    @Query("from HistoricoFrete where executor = :executor")
    public List<HistoricoFrete> findByExecutor(@Param("executor") Usuario executor);

}
