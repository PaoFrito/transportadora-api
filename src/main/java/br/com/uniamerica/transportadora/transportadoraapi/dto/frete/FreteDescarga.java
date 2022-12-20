package br.com.uniamerica.transportadora.transportadoraapi.dto.frete;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class FreteDescarga extends FreteAualizado{

    @Getter @Setter
    private int quilometragemFim;

    @Getter @Setter
    private BigDecimal pesoFinal;
}
