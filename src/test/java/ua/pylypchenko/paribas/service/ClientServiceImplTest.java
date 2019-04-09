package ua.pylypchenko.paribas.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.pylypchenko.paribas.domain.Client;
import ua.pylypchenko.paribas.repository.ClientRepository;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ClientServiceImplTest {


    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    private Client client;

    @Before
    public void setUp() throws Exception {
        client = new Client();
        client.setLastName("lastName");
        client.setFirstName("firstName");
        client.setMiddleName("middleName");
        client.setInn(123L);
    }


    @Test
    public void save() throws Exception {
        // given
        when(clientRepository.save(client)).then(invocationOnMock -> {
            client.setId(1L);
            return client;
        });

        // when
        Client savedClient =  clientService.save(client);

        // then
        Assert.assertTrue(savedClient.getId() == 1L);
        verify(clientRepository, times(1)).save(client);
    }

    @Test
    public void getClientByInn() throws Exception {
        //given
        when(clientRepository.findClientByInn(123L))
                .thenReturn(client)
                .thenReturn(null);

        //when
        Client clientFromDb = clientService.getClientByInn(123L);

        //then
        Assert.assertNotNull(clientFromDb);
        Assert.assertEquals(client, clientFromDb);
        Assert.assertNull(clientService.getClientByInn(1L));
        verify(clientRepository, times(2)).findClientByInn(anyLong());
    }

}