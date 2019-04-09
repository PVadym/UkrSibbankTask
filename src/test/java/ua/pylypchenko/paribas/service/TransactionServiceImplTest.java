package ua.pylypchenko.paribas.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import ua.pylypchenko.paribas.domain.Transaction;
import ua.pylypchenko.paribas.repository.TransactionRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyIterable;
import static org.mockito.ArgumentMatchers.anyIterableOf;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class TransactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    private List<Transaction> transactions;
    private Transaction trnOne;
    private Transaction trnTwo;


    @Before
    public void setUp() throws Exception {
        trnOne = new Transaction();
        trnOne.setPlace("A");
        trnOne.setAmount(10.0);
        trnOne.setCard("12**9");

        trnTwo = new Transaction();
        trnTwo.setPlace("B");
        trnTwo.setAmount(20.0);
        trnTwo.setCard("23**9");

        transactions = Arrays.asList(trnOne,trnTwo);
    }

    @Test
    public void saveAll() throws Exception {
        //given
        when(transactionRepository.saveAll(anyIterable())).thenReturn(transactions);

        //when
        Iterable<Transaction> savedTransactions = transactionService.saveAll(transactions);

        //then
        Assert.assertNotNull(savedTransactions);
        verify(transactionRepository, times(1)).saveAll(transactions);
    }

}