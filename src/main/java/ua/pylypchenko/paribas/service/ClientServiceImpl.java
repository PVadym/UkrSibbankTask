package ua.pylypchenko.paribas.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.pylypchenko.paribas.domain.Client;
import ua.pylypchenko.paribas.repository.ClientRepository;

/**
 * Implementation of {@link ClientService} interface for {@link Client} entity
 * @author pylypchenko
 */

@Slf4j
@Service
public class ClientServiceImpl implements ClientService{

    private final  ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Store client into database
     * @param client Client to store
     * @return saved entity with Id
     */
    @Override
    public Client save(Client client) {
        log.info("Client to save in DB = {}", client);
        return clientRepository.save(client);
    }

    /**
     * Get client by INN(unique identification code)
     * @param inn  identification code for searching by
     * @return founded client from DB or null in case client is not exists
     */
    @Override
    @Transactional(readOnly = true)
    public Client getClientByInn(long inn) {
        log.info("Search by INN = {}", inn);
        return clientRepository.findClientByInn(inn);
    }
}
