package br.com.uniamerica.transportadora.transportadoraapi.dto.frete;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class FreteEmTransporte extends FreteAualizado {

    @Getter @Setter
    private int quilometragemIni;

    @Getter @Setter
    private BigDecimal pesoInicial;
}