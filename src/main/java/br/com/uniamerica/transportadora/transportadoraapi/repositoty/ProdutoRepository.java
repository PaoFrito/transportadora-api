package br.com.uniamerica.transportadora.transportadoraapi.repositoty;


import br.com.uniamerica.transportadora.transportadoraapi.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("from Produto where id = :id")
    public List<Produto> findById();

    @Query("SELECT produto FROM Produto produto WHERE produto.ativo = true")
    public List<Produto> findByProdutosAtivos();

    @Query("from Produto where nome = :nome")
    public List<Produto> findByNome(@Param("nome") String nome);
}
