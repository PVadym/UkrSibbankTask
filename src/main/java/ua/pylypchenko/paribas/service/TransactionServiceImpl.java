package ua.pylypchenko.paribas.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.pylypchenko.paribas.domain.Transaction;
import ua.pylypchenko.paribas.repository.TransactionRepository;

import java.util.List;

/**
 * Implementation of {@link TransactionService} interface for {@link Transaction} entity
 * @author pylypchenko
 */

@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }


    /**
     * Save list of transactions
     * @param transactions lit to save
     * @return saved list;
     */
    @Override
    public Iterable<Transaction> saveAll(List<Transaction> transactions) {
        log.info("Count transactions to save = {}", transactions.size());
        return transactionRepository.saveAll(transactions);
    }
}
