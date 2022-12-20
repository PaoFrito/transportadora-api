package br.com.uniamerica.transportadora.transportadoraapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "td_tiposdespesas", schema = "transportadora")
@NoArgsConstructor
public class TipoDespesa extends AbstractEntity {
@Getter @Setter
@Column(name = "nome", length = 25, nullable = false)
private String nome;
}
