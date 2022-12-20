package br.com.uniamerica.transportadora.transportadoraapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "td_estados", schema = "transportadora")
@NoArgsConstructor
public class Estado extends AbstractEntity {
    @Getter @Setter
    @Column(name = "nome", length = 20, nullable = false,unique = true)
    private String nome;
}
