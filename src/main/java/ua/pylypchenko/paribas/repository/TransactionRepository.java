package ua.pylypchenko.paribas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.pylypchenko.paribas.domain.Transaction;

/**
 * Repository interface for {@link Transaction} entity
 *
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
