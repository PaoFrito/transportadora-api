package br.com.uniamerica.transportadora.transportadoraapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "td_usuario", schema = "transportadora")
@NoArgsConstructor
public class Usuario extends AbstractEntity {

    @Getter @Setter
    @Column(name = "perc_ganho", nullable = false, scale = 3, precision = 5)
    private BigDecimal percGanho;

    @Getter @Setter
    @Column(name = "login", length = 30, nullable = false, unique = true)
    private String login;

    @Getter @Setter
    @Column(name = "senha", length = 30, nullable = false)
    private String senha;

    @Getter @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "grupo", length = 25, nullable = false)
    private Grupo grupo;

    @Getter @Setter
    @Column(name = "nome", length = 50, nullable = false,unique = true)
    private String nome;

    @Getter @Setter
    @Column(name = "cpf", length = 11, nullable = false,unique = true)
    private String cpf;

    @Getter @Setter
    @Column(name = "telefone", length = 11,nullable = false, unique = true)
    private String telefone;

    @Getter @Setter
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Getter @Setter
    @Column(name = "endereco", length =60, nullable = false )
    private String endereco;

    @Getter @Setter
    @Column(name = "observacao",  length = 255, nullable = false)
    private String observacao;
}
