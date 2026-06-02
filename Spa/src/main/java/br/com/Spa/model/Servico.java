package br.com.Spa.model;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private int duracao;

    @Column(nullable = false)
    private double precoServico;

    @Column(nullable = false)
    private String tipoServico;

    @Column(nullable = false)
    private String descricaoServico;

}
