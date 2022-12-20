package br.com.uniamerica.transportadora.transportadoraapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "td_despesas", schema = "transportadora",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id_motorista", "id_frete"})
})
@NoArgsConstructor
public class Despesa extends AbstractEntity {
    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_tipoDespesa", nullable = false)
    private TipoDespesa tipoDespesa;

    @Getter @Setter
    @Column(name = "valor", nullable = false, scale = 3, precision = 5)
    private BigDecimal valor;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_motorista",nullable = false)
    private Usuario motorista;

    @Getter @Setter
    @Column(name = "data", nullable = true)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime data = LocalDateTime.now();

    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "id_aprovador", nullable = true, unique = false)
    private Usuario aprovador;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "id_frete", nullable = false, unique = false)
    private Frete frete;

}
