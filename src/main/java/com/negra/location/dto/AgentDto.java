package com.negra.location.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

import static com.negra.location.utility.ErrorMessage.*;
import static com.negra.location.utility.Pattern.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgentDto implements Serializable {

    @NotNull(message = ERROR_SEND_DATA)
    @NotBlank(message = ERROR_USER_EMAIL_EMPTY)
    @Email(message = ERROR_USER_EMAIL_INVALID)
    @Pattern(regexp = PATTERN_USER_EMAIL, message = ERROR_USER_EMAIL_INVALID)
    private String email;

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_USER_TEL, message = ERROR_USER_TEL_INVALID)
    private String tel;

    private String hiddenTelNumber;

    private AddressDto address;

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_RS_AGENCE, message = ERROR_AGENT_RS)
    private String rsAgence;

    private String notoriety;
}
