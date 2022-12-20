package br.com.uniamerica.transportadora.transportadoraapi.repositoty;


import br.com.uniamerica.transportadora.transportadoraapi.entity.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {

    @Query("from Marca where id = :id")
    public List<Marca> findById();

    @Query("from Marca where ativo = :ativo")
    public List<Marca> findByAtivoTrue();

    @Query("from Marca where nome = :nome")
    public List<Marca> findByNome(@Param("nome") String nome);




}
