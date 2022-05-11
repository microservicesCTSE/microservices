package com.pos.identity.transformer;

import com.pos.identity.domain.Client;
import com.pos.identity.dto.ClientDto;

import org.springframework.stereotype.Component;

@Component
public class ClientTransformer implements BaseTransformer<Client, ClientDto> {
    @Override
    public ClientDto tranform(Client client) {

        ClientDto clientDto = new ClientDto();
        clientDto.setClientId(client.getClientId());
        clientDto.setClientName(client.getClientName());
        clientDto.setClientCode(client.getClientCode());

        return clientDto;
    }

    @Override
    public Client reverseTranform(ClientDto type) {
        return null;
    }
}
