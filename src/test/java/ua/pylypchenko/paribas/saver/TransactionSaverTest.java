package ua.pylypchenko.paribas.saver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import ua.pylypchenko.paribas.domain.Client;
import ua.pylypchenko.paribas.parser.TransactionParser;
import ua.pylypchenko.paribas.parser.XMLParser;
import ua.pylypchenko.paribas.service.ClientService;
import ua.pylypchenko.paribas.service.TransactionService;

import java.io.FileNotFoundException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class TransactionSaverTest {

    @Mock
    private TransactionService transactionService;
    @Mock
    private ClientService clientService;

    @InjectMocks
    private TransactionSaver transactionSaver;

    private String fileName = "src/test/resources/test.xml";

    @Test
    public void parseAndSaveSuccess() throws Exception {
        transactionSaver.parseAndSave(fileName);

        verify(clientService,times(12))
                .getClientByInn(anyLong());
        verify(clientService,atLeastOnce())
                .save(any(Client.class));
        verify(transactionService, only())
                .saveAll(anyList());
    }


    @Test()
    public void parseAndSaveFail() throws Exception {

        transactionSaver.parseAndSave("");
        verify(clientService,never())
                .getClientByInn(anyLong());
        verify(clientService,never())
                .save(any(Client.class));
        verify(transactionService, never())
                .saveAll(anyList());
    }

}