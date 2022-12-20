package br.com.uniamerica.transportadora.transportadoraapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "td_historicosfretes", schema = "transportadora")
@NoArgsConstructor
public class HistoricoFrete extends AbstractEntity {

    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_frete", nullable = false)
    @JsonIgnore
    private Frete frete;

    @Getter @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "statusFrete", length = 30, nullable = false)
    private StatusFrete statusFrete;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_executor", nullable = false)
    private Usuario executor;
}
