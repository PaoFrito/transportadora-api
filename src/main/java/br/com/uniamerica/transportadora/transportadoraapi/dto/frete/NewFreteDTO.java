package br.com.uniamerica.transportadora.transportadoraapi.dto.frete;

import br.com.uniamerica.transportadora.transportadoraapi.entity.Caminhao;
import br.com.uniamerica.transportadora.transportadoraapi.entity.Cidade;
import br.com.uniamerica.transportadora.transportadoraapi.entity.Produto;
import br.com.uniamerica.transportadora.transportadoraapi.entity.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class NewFreteDTO {

    @Getter @Setter
    private Cidade cidadeOrigem;

    @Getter @Setter
    private Cidade cidadeDestino;

    @Getter @Setter
    private Usuario motorista;

    @Getter @Setter
    private Caminhao caminhao;

    @Getter @Setter
    private Produto produto;

    @Getter @Setter
    private BigDecimal precoTonelada;

    @Getter @Setter
    private Usuario executor;

}
