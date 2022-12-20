package br.com.uniamerica.transportadora.transportadoraapi.service;

import br.com.uniamerica.transportadora.transportadoraapi.entity.Modelo;
import br.com.uniamerica.transportadora.transportadoraapi.entity.Produto;
import br.com.uniamerica.transportadora.transportadoraapi.repositoty.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class ProdutoService {

    @Autowired
    public ProdutoRepository produtoRepository;

    public Produto findById(Long id){
        return this.produtoRepository.findById(id).orElse(new Produto());
    }

    public List<Produto> findAll(){
        return this.produtoRepository.findAll();
    }

    @Transactional
    public Produto save(Produto produto){
        return this.produtoRepository.save(produto);
    }


    @Transactional
    public void atualizar(final Long id,final Produto produto){
        if (id.equals(produto.getId()) && !this.produtoRepository.findById(id).isEmpty()){
            this.produtoRepository.save(produto);
        }else{
            throw new RuntimeException("Id não encontrado");
        }
    }

    @Transactional
    public void excluir(final Long id, final Produto produto){
        if (id.equals(produto.getId()) && !this.produtoRepository.findById(id).isEmpty()){
            this.produtoRepository.delete(produto);
        }else{
            throw new RuntimeException("Id não encontrado");
        }
    }

}
