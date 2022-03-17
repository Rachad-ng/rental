package com.negra.location.dto;

import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

import static com.negra.location.utility.ErrorMessage.*;
import static com.negra.location.utility.Pattern.PATTERN_EMAIL;

@Data
public class UserLoginDto implements Serializable {

    @NotNull(message = ERROR_SENDS_DATA)
    @NotBlank(message = ERROR_UTILISATEUR_EMAIL_EMPTY)
    @Email(message = ERROR_UTILISATEUR_EMAIL_INVALID)
    @Pattern(regexp = PATTERN_EMAIL, message = ERROR_UTILISATEUR_EMAIL_INVALID)
    private String email;

    @NotNull(message = ERROR_SENDS_DATA)
    @Size(min = 3, message = ERROR_UTILISATEUR_PASSWORD_INVALID)
    private String password;

    // Getters and Setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
