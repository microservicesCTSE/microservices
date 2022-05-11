package com.pos.inventory.repository.transfomer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.pos.inventory.common.dto.ClientDto;
import com.pos.inventory.repository.domain.Client;

@Component
public class ClientTransformer implements BaseTransformer<Client, ClientDto> {

//	@Value("${imageupload.url}")
//	private String imgUrl;
//
//	@Value("${imageupload.path}")
//	private String imgPath;

	@Override
	public ClientDto transform(Client client) {
		ClientDto clientDto = null;
		if (client != null) {
			clientDto = new ClientDto();
			clientDto.setClientId(client.getClientId());
			clientDto.setFirstName(client.getFirstName());
			clientDto.setLastName(client.getLastName());
			clientDto.setIsActive(client.getIsActive());
			clientDto.setLogo(client.getLogo());
		}

		return clientDto;
	}

	@Override
	public Client reverseTransform(ClientDto clientDto) {
		Client client = null;
		if (clientDto != null) {
			client = new Client();
			client.setClientId(clientDto.getClientId());
			client.setFirstName(clientDto.getFirstName());
			client.setLastName(clientDto.getLastName());
			client.setIsActive(clientDto.getIsActive());
			if (clientDto.getLogo() != null) {
				client.setLogo(clientDto.getLogo());
			}
		}
		return client;
	}
}
