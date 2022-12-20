package br.com.uniamerica.transportadora.transportadoraapi.repositoty;

import br.com.uniamerica.transportadora.transportadoraapi.entity.Cidade;
import br.com.uniamerica.transportadora.transportadoraapi.entity.Estado;
import br.com.uniamerica.transportadora.transportadoraapi.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    @Query("from Cidade where id = :id")
    public List<Cidade> findById();

    @Query("SELECT cidade FROM Cidade cidade WHERE cidade.ativo = true")
    public List<Cidade> findByCidadesAtivos();

    @Query("from Cidade where nome = :nome")
    public List<Cidade> findByNome(@Param("nome") String nome);

    @Query("from Cidade cidade where cidade.estado.id = :estadoId")
    public List<Cidade> findByEstado(@Param("estadoId") Long estadoId);

}
