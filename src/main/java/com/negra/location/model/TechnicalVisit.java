package com.negra.location.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class TechnicalVisit extends Cost implements Serializable {

    public TechnicalVisit(LocalDateTime dueDate, int amount){
        this.startDate = LocalDateTime.now();
        this.dueDate = dueDate;
        this.amount =  amount;
    }

}