package br.com.uniamerica.transportadora.transportadoraapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "td_modelos", schema = "transportadora")
@NoArgsConstructor
public class Modelo extends AbstractEntity {
    @Getter @Setter
    @Column(name = "nome", length = 30, nullable = false)
    private String nome;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_marca", nullable = false)
    @JsonIgnore
    private Marca marca;
}
