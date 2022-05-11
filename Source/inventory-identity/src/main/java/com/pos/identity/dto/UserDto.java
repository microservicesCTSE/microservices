package com.pos.identity.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserDto implements Serializable {

    private static final long serialVersionUID = 4101145558970537539L;

    private Long id;

    private String companyName;

    private String companyReg;

    private String ownerName;

    private String contactNo;

    private String emailAddress;

    private String address;

    private String country;

    private String referenceNo;

    private String password;

    private String userName;

    private Boolean isApproved;

    private String reasonForDecline;

    private Boolean isLocked;

    private LocalDateTime lockedDateTime;

    private Integer failedAttempt;

    private Boolean isActive;

    private Boolean isTfaEnabled;

    private String tfaCode;

    private Double tfaExpTime;

    private String tfaDefaultType;

//    private Long userTypeFk;
    private UserTypeDto userTypeDto;

    private Long clientId;
    
    private Boolean isVerified;
 
    private String verificationCode;
   
    private LocalDateTime expiredAt;
    
    private LocalDateTime verifiedOn;

}
