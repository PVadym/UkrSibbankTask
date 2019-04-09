package ua.pylypchenko.paribas.parser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.pylypchenko.paribas.domain.Client;
import ua.pylypchenko.paribas.service.ClientServiceImpl;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class TransactionParserTest {

    private String fileName = "src/test/resources/test.xml";

    @Mock
    private ClientServiceImpl clientService;

    @InjectMocks
    private TransactionParser transactionParser;

    @Before
    public void setUp() throws Exception {
        InputStream inputStream = new FileInputStream(fileName);
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(inputStream);
        transactionParser.setReader(reader);
        transactionParser.parse();
    }


    @Test
    public void parseAndSaveClient() throws Exception {
        verify(clientService,times(12))
                .getClientByInn(anyLong());
        verify(clientService,atLeast(3))
                .save(any(Client.class));
    }

    @Test
    public void getParsedObject() throws Exception {
        assertNotNull(transactionParser.getParsedObject());
        assertTrue(transactionParser.getParsedObject().size() == 12);
    }

}