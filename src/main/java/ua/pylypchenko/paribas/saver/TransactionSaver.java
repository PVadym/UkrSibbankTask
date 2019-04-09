package ua.pylypchenko.paribas.saver;

import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.internal.util.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.pylypchenko.paribas.domain.Transaction;
import ua.pylypchenko.paribas.parser.TransactionParser;
import ua.pylypchenko.paribas.parser.XMLParser;
import ua.pylypchenko.paribas.service.ClientService;
import ua.pylypchenko.paribas.service.TransactionService;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

/**
 * Service for parse and save transactions to database
 * @author pylypchenko
 */

@Slf4j
@Service
public class TransactionSaver {

    private final TransactionService transactionService;
    private final ClientService clientService;
    private XMLParser parser;

    @Autowired
    public TransactionSaver(TransactionService transactionService, ClientService clientService) {
        this.transactionService = transactionService;
        this.clientService = clientService;
    }

    /**
     * Parse file and save parsed data to DB
     *
     * @param fileName name of the XML file
     */
    public void parseAndSave(String fileName){
        initParser(fileName);
        if(Objects.nonNull(parser)) {
            try {
                parser.parse();

                List<Transaction> transactionsToSave = (List<Transaction>) parser.getParsedObject();

                log.info("Count transactions after parsing = {}", transactionsToSave.size());

                transactionService.saveAll(transactionsToSave);

                log.info("All transactions saved succesfully!");

            } catch (XMLStreamException e) {
                log.warn("Exception is occurred while parsing with cause = {}", ExceptionUtils.getRootCause(e));
            }
        }
    }


    private void initParser(String fileName) {
        try {
            InputStream inputStream = new FileInputStream(fileName);
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(inputStream);
            parser = new TransactionParser(reader, clientService);
        } catch (FileNotFoundException | XMLStreamException e) {
            log.warn("Exception is occurred with cause = {}", ExceptionUtils.getRootCause(e));
        }

    }
}
