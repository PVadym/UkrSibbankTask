package ua.pylypchenko.paribas.parser;

import org.springframework.stereotype.Service;
import ua.pylypchenko.paribas.domain.Client;
import ua.pylypchenko.paribas.domain.Currency;
import ua.pylypchenko.paribas.domain.Transaction;
import ua.pylypchenko.paribas.service.ClientService;

import javax.xml.stream.XMLStreamReader;
import java.util.*;

/**
 * Implemetation of {@link XMLParser} interface for {@link Transaction} entity
 * @author pylypchenko
 */

public class TransactionParser implements XMLParser {


    private StringBuilder stringBuilder;
    private XMLStreamReader reader;
    private ClientService clientService;

    private List<Transaction> transactions = new ArrayList<>();

    private Transaction currentTransaction;
    private Client currentClient;

    public TransactionParser(XMLStreamReader reader, ClientService clientService){
        this.stringBuilder = new StringBuilder();
        this.setReader(reader);
        this.clientService = clientService;
    }

    @Override
    public void parseStartElement(String nodeName) {
        if(nodeName.equals("transactions")){
            transactions = new ArrayList<>();
        } else if (nodeName.equals("transaction")) {
            currentTransaction = new Transaction();
        }else if (nodeName.equals("client")) {
            currentClient = new Client();
        }
    }


    @Override
    public void parseEndElement(String nodeName) {
        String textValue = extractStringFromBuilder();
        if (nodeName.equals("transactions")) {

        } else if (nodeName.equals("transaction")) {
            transactions.add(currentTransaction);
            currentTransaction = null;
        } else if (nodeName.equals("place")) {
            currentTransaction.setPlace(textValue);
        } else if (nodeName.equals("amount")) {
            currentTransaction.setAmount(Double.parseDouble(textValue));
        } else if (nodeName.equals("currency")) {
            currentTransaction.setCurrency(Currency.valueOf(textValue));
        } else if (nodeName.equals("card")) {
            currentTransaction.setCard(textValue);

        } else if (nodeName.equals("firstName")) {
            currentClient.setFirstName(textValue);
        } else if (nodeName.equals("lastName")) {
            currentClient.setLastName(textValue);
        } else if (nodeName.equals("middleName")) {
            currentClient.setMiddleName(textValue);
        } else if (nodeName.equals("inn")) {
            currentClient.setInn(Long.parseLong(textValue));
        } else if (nodeName.equals("client")) {
            if (Objects.nonNull(currentTransaction) && Objects.nonNull(currentClient)) {
                Client clientFromDb = clientService.getClientByInn(currentClient.getInn());
                if(Objects.nonNull(clientFromDb)){
                    currentTransaction.setClient(clientFromDb);
                } else {
                    Client savedClient = clientService.save(currentClient);
                    currentTransaction.setClient(savedClient);
                }
            }
            currentClient = null;
        }
    }


    @Override
    public void parseText(String text) {
        stringBuilder.append(text);
    }

    @Override
    public void setReader(XMLStreamReader reader) { this.reader = reader; }

    @Override
    public XMLStreamReader getReader() {
        return reader;
    }

    @Override
    public List<Transaction> getParsedObject() {
        return Objects.isNull(transactions)? new ArrayList<>() : transactions;
    }

    private String extractStringFromBuilder(){
        String result = stringBuilder.toString().trim();
        stringBuilder.setLength(0);
        return result;
    }


}
