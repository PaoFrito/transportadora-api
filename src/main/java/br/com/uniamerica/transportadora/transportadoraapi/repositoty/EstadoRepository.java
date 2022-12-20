package br.com.uniamerica.transportadora.transportadoraapi.repositoty;


import br.com.uniamerica.transportadora.transportadoraapi.entity.Caminhao;
import br.com.uniamerica.transportadora.transportadoraapi.entity.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

    @Query("from Estado where id = :id")
    public List<Estado> findById();

    @Query("SELECT estado FROM Estado estado WHERE estado.ativo = true")
    public List<Estado> findByEstadosAtivos();

    @Query("from Estado where nome = :nome")
    public List<Estado> findByNome(@Param("nome") String nome);

}
