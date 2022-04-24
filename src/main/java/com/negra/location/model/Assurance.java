package com.negra.location.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class Assurance extends Cost {

    public Assurance(LocalDateTime dueDate, int amount){
        this.startDate = LocalDateTime.now();
        this.dueDate = dueDate;
        this.amount =  amount;
    }

}