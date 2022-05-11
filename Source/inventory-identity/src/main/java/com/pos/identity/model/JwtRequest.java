package com.pos.identity.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
public class JwtRequest implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    private String username;
    private String password;
    private String grantType;
    private String refreshToken;
    private String agentRegNumber;
}
