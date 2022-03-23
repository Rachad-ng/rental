package com.negra.location.model;

import lombok.Data;

import javax.persistence.Entity;
import java.io.Serializable;

@Data
@Entity
public class TechnicalVisit extends Cost implements Serializable {
}