package io.github.davaosantos.domain.entity;

import io.github.davaosantos.domain.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pedido")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "data_pedido")
    private LocalDate dtPedido;


    //Precision -> Quantidade de digitos
    //Scale -> Quantidade de numeros após a virgula , casas decimais
    @Column(name = "total", precision = 20, scale = 2)
    private BigDecimal total;

    @OneToMany(mappedBy = "pedido")
    List<ItemPedido> itemPedidos;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusPedido status;

}
