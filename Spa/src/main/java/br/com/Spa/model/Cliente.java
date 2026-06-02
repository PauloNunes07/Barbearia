package br.com.Spa.model;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter @Setter
@NoArgsConstructor

public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private int  idade;

    private String email;

    @Column(nullable = false)
    private String telefone;

}
