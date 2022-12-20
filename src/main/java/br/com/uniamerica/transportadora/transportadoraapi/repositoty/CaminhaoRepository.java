package br.com.uniamerica.transportadora.transportadoraapi.repositoty;

import br.com.uniamerica.transportadora.transportadoraapi.entity.*;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CaminhaoRepository extends JpaRepository<Caminhao, Long> {

    @Query("from Caminhao where id = :id")
    public Caminhao findById();

    @Query("FROM Caminhao WHERE ativo is true")
    public List<Caminhao> findByCaminhoesAtivos();

    @Query("from Caminhao where placa = :placa")
    public List<Caminhao> findByPlaca(@Param("placa") String placa);

    @Query("from Caminhao where modelo = :modelo")
    public List<Caminhao> findByModelo(@Param("modelo") Modelo modelo);

    @Query("from Caminhao where motorista = :motorista")
    public List<Caminhao> findByMotorista(@Param("motorista") Usuario motorista);

    @Query("from Caminhao where ano = :ano")
    public List<Caminhao> findByAno(@Param("ano") int ano);

    @Query("from Caminhao where observacao = :observacao")
    public List<Caminhao> findByObservacao(@Param("observacao") String observacao);

    @Query("from Caminhao where cor = :cor")
    public List<Caminhao> findByCor(@Param("cor") Cor cor);



}

