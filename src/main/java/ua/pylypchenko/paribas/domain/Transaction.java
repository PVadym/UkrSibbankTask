package ua.pylypchenko.paribas.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

/**
 * Class for Transaction entity
 * @author pylypchenko
 */

@Data
@ToString
@EqualsAndHashCode
@Entity(name = "transaction")
public class Transaction {

    @Id
    @SequenceGenerator(name="trans_seq", sequenceName="trans_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trans_seq")
    private Long id;

    private String place;

    private Double amount;

    @Enumerated(value = EnumType.STRING)
    private Currency currency;

    private String card;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
