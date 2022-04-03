package com.negra.location.dto;

import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

import static com.negra.location.utility.ErrorMessage.*;
import static com.negra.location.utility.Pattern.*;

@Data
public class AgentRegistrationDto implements Serializable {

    @NotNull(message = ERROR_SEND_DATA)
    @NotBlank(message = ERROR_USER_EMAIL_EMPTY)
    @Email(message = ERROR_USER_EMAIL_INVALID)
    @Pattern(regexp = PATTERN_USER_EMAIL, message = ERROR_USER_EMAIL_INVALID)
    private String email;

    @NotNull(message = ERROR_SEND_DATA)
    @Size(min = 3, message = ERROR_USER_PASSWORD_INVALID)
    private String password;

    @NotNull(message = ERROR_SEND_DATA)
    @Size(min = 3, message = ERROR_USER_PASSWORD_INVALID)
    private String passwordConfirmed;

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_USER_ROLE, message = ERROR_USER_ROLE_INVALID)
    private String role;

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_USER_TEL, message = ERROR_USER_TEL_INVALID)
    private String tel;

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_USER_NAME, message = ERROR_USER_LASTNAME_INVALID)
    private String lastname;

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_USER_NAME, message = ERROR_USER_FIRSTNAME_INVALID)
    private String firstname;

    private AddressDto addressDto;

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_RS_AGENCE, message = ERROR_AGENT_RS)
    private String rsAgence;

}
