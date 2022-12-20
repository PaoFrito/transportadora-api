package br.com.uniamerica.transportadora.transportadoraapi.repositoty;



import br.com.uniamerica.transportadora.transportadoraapi.entity.TipoDespesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Past;
import java.util.List;

@Repository
public interface TipoDespesaRepository extends JpaRepository<TipoDespesa, Long> {

    @Query("from TipoDespesa where id = :id")
    public List<TipoDespesa> findById();

    @Query("from TipoDespesa where ativo is true")
    public List<TipoDespesa> findByAtivoTrue();

    @Query("from TipoDespesa where nome = :nome")
    public List<TipoDespesa> findByNome(@Param("nome") String nome);
}
