package br.com.Spa.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
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
