package com.negra.location.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static com.negra.location.utility.ErrorMessage.*;
import static com.negra.location.utility.Pattern.*;

@Entity
@Table(name = "user")
@Data
public abstract class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "user")
    private Set<Booking> bookingSet = new HashSet<>();

    @NotNull(message = ERROR_SEND_DATA)
    @NotBlank(message = ERROR_USER_EMAIL_EMPTY)
    @Email(message = ERROR_USER_EMAIL_INVALID)
    @Pattern(regexp = PATTERN_USER_EMAIL, message = ERROR_USER_EMAIL_INVALID)
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull(message = ERROR_SEND_DATA)
    @Size(min = 3, message = ERROR_USER_PASSWORD_INVALID)
    @Column(nullable = false)
    private String password;

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_USER_ROLE, message = ERROR_USER_ROLE_INVALID)
    @Column(nullable = false)
    private String role;

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp= PATTERN_USER_TEL, message= ERROR_USER_TEL_INVALID)
    @Column(nullable = false)
    private String tel;

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_USER_NAME, message = ERROR_USER_LASTNAME_INVALID)
    @Column(nullable = false)
    private String lastname;

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_USER_NAME, message = ERROR_USER_FIRSTNAME_INVALID)
    @Column(nullable = false)
    private String firstname;

    @NotNull(message = ERROR_USER_REGISTRATION_DATE_REQUIRED)
    @Past(message = ERROR_USER_REGISTRATION_DATE_INVALID)
    @Basic
    @Column(nullable = false)
    private LocalDateTime registrationDate;

    @Column(nullable = false)
    private Boolean active;

    // Constructeur (Inistialisation de la date d'inscription)

    public User(){
        this.active = true;
        this.setRegistrationDate( LocalDateTime.now() );
    }

    // Gestion des relations bi-directionnels

    public void addBooking(Booking booking){
        booking.setUser(this);
        this.getBookingSet().add(booking);
    }

    public void removeBooking(Booking booking){
        this.getBookingSet().remove(booking);
    }
}