package br.com.uniamerica.transportadora.transportadoraapi.repositoty;


import br.com.uniamerica.transportadora.transportadoraapi.entity.Marca;
import br.com.uniamerica.transportadora.transportadoraapi.entity.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Long> {

    @Query("from Modelo where id = :id")
    public List<Modelo> findById();

    @Query("from Modelo where ativo = :ativo")
    public List<Modelo> findByAtivoTrue();

    @Query("from Modelo where nome = :nome")
    public List<Modelo> findByNome(@Param("nome") String nome);

    @Query("from Modelo modelo where modelo.marca.id = :marcaId")
    public List<Modelo> findByMarca(@Param("marcaId") Long marcaId);

}
