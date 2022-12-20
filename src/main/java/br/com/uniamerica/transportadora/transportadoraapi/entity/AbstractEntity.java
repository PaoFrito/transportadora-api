package br.com.uniamerica.transportadora.transportadoraapi.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    @Column(name = "id", length = 25,nullable = false, unique = true)
    private Long id;

    @Getter @Setter
    @Column(name = "cadastro", nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime cadastro;

    @Getter @Setter
    @Column(name = "atualizado", length = 25, nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime atualizado;

    @Getter @Setter
    @Column(name = "ativo", nullable = false)
    private boolean ativo;

    @PrePersist
    public void dataCadastro(){
        this.setCadastro(LocalDateTime.now());
        this.setAtualizado(LocalDateTime.now());
        this.setAtivo(true);
    }

    @PreUpdate
    public void dataAtualizado(){
        this.setAtualizado(LocalDateTime.now());
    }
}
