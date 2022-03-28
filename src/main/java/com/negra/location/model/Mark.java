package com.negra.location.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static com.negra.location.utility.ErrorMessage.ERROR_MARK_LIBELLE;
import static com.negra.location.utility.ErrorMessage.ERROR_SEND_DATA;
import static com.negra.location.utility.Pattern.PATTERN_MARk;

@Data
@Entity
@Table(name = "mark")
public class Mark implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_MARk, message = ERROR_MARK_LIBELLE)
    @Column(nullable = false)
    private String libelle;

    @OneToMany(mappedBy = "mark")
    private Set<Model> modelSet = new HashSet<>();

    // Gestion d'une relation bi-directionnel

    public void addModel(Model model){
        model.setMark(this);
        this.getModelSet().add(model);
    }

    public void removeModel(Model model){
        this.getModelSet().remove(model);
    }
}