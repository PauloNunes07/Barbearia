package br.com.Spa.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @OneToOne
    @JoinColumn(name = "id_agendamento", nullable = false)
    private Agendamento agendamento;

    @Column(nullable = false)
    private int notaAvaliacao;

    private String comentario;
}
