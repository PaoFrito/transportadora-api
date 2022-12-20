package br.com.uniamerica.transportadora.transportadoraapi.dto.frete;

import br.com.uniamerica.transportadora.transportadoraapi.entity.StatusFrete;
import br.com.uniamerica.transportadora.transportadoraapi.entity.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class FreteAualizado {

    @Getter @Setter
    private Long id;

    @Getter @Setter
    private Usuario executor;
}