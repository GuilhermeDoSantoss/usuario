package com.guilherme.agendador_tarefas.infrastructure.enitity;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "telefone")
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "numero", length = 10)
    private String numero;
    @Column(name = "ddd", length = 5)
    private String ddd;
    @Column(name = "usuario_id")
    private Long usuario_id;

    public static Object biulder() {
    }
}