package io.github.davaosantos.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "produto")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty(message = "Campo descrição é obrigatorio")
    @Column(name = "descricao")
    private String descricao;

    @Column(name = "preco")
    @NotNull(message = "Campo preço é obrigatorio")
    private BigDecimal preco;

}
