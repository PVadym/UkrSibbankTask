package ua.pylypchenko.paribas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.pylypchenko.paribas.domain.Client;

/**
 * Repository interface for {@link Client} entity
 *
 */
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findClientByInn(long inn);
}
