package ua.pylypchenko.paribas.service;

import ua.pylypchenko.paribas.domain.Client;

/**
 * Service interface for {@link Client}
 */

public interface ClientService {

    Client save(Client client);

    Client getClientByInn(long inn);
}
