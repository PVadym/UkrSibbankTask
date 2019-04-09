package ua.pylypchenko.paribas.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

/**
 * Class for entity Client
 * @author pylypchenko
 */
@Data
@ToString
@EqualsAndHashCode
@Entity(name = "client")
public class Client {

    @Id
    @SequenceGenerator(name="client_seq", sequenceName="client_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_seq")
    private Long id;

    private String firstName;

    private String lastName;

    private String middleName;

    private long inn;


}
