package ua.pylypchenko.paribas.service;

import ua.pylypchenko.paribas.domain.Transaction;
import java.util.List;

/**
 * Service interface for {@link Transaction}
 */

public interface TransactionService {

    Iterable<Transaction> saveAll(List<Transaction> transactions);
}
