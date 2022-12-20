package br.com.uniamerica.transportadora.transportadoraapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "td_caminhoes", schema = "transportadora", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"placa", "id_modelo"})
})
@NoArgsConstructor
public class Caminhao extends AbstractEntity {

    @Getter @Setter
    @Column(name = "placa", length = 7,nullable = false,unique = true)
    private String placa;

    @Getter @Setter
    @JoinColumn(name = "id_motorista", nullable = false)
    @ManyToOne
    private Usuario motorista;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_modelo", nullable = false)
    @Getter @Setter
    private Modelo modelo;

    @Getter @Setter
    @Column(name = "ano", length = 4,nullable = false)
    private int ano;

    @Getter @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "cor", length = 20, nullable = false)
    private Cor cor;

    @Getter @Setter
    @Column(name = "observacao",  length = 255, nullable = false)
    private String observacao;

}
