package br.com.uniamerica.transportadora.transportadoraapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
@Entity
@Table(name = "td_fretes", schema = "transportadora")
@NoArgsConstructor
public class Frete extends AbstractEntity {
    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cidadeOrigem", nullable = false)
    private Cidade cidadeOrigem;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cidadeDestino", nullable = false)
    private Cidade cidadeDestino;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_motorista", nullable = false)
    private Usuario motorista;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_caminhao", nullable = false)
    private Caminhao caminhao;

    @Getter @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "statusFrete", length = 255,nullable = false)
    private StatusFrete statusFrete;

    @Getter @Setter
    @Column(name = "quilometragremIni")
    private int quilometragemIni;

    @Getter @Setter
    @Column(name = "quilometragemFim")
    private int quilometragemFim;

    @Getter @Setter
    @Column(name = "totalBrutoRecebidoNota",  nullable = true, scale = 3, precision = 5)
    private BigDecimal totalBrutoRecebidoNota;

    @Getter @Setter
    @Column(name = "totalLiquidoRecebido",  nullable = true, scale = 3, precision = 5)
    private BigDecimal totalLiquidoRecebido;

    @Getter @Setter
    @Column(name = "pesoInicial",  nullable = true, scale = 3, precision = 5)
    private BigDecimal pesoInicial;

    @Getter @Setter
    @Column(name = "pesoFinal",  nullable = true, scale = 3, precision = 5)
    private BigDecimal pesoFinal;

    @Getter @Setter
    @Column(name = "pesoFinalTransportado",  nullable = true, scale = 3, precision = 5)
    private BigDecimal pesoFinalTransportado;

    @Getter @Setter
    @Column(name = "precoTonelada", nullable = false, scale = 3, precision = 5)
    private BigDecimal precoTonelada;

    @Getter @Setter
    @Column(name = "observacao", nullable = true)
    private String observacao;
}
