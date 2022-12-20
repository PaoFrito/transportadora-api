package br.com.uniamerica.transportadora.transportadoraapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "td_produtos", schema = "transportadora")
@NoArgsConstructor
public class Produto extends AbstractEntity {
    @Getter @Setter
    @Column(name = "nome", length = 20, nullable = false)
    private String nome;
}
