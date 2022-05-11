package com.pos.identity.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ClientDto implements Serializable {

	private static final long serialVersionUID = 5361766360422193892L;

	private Long clientId;

	private String clientName;

	private String clientCode;

}
