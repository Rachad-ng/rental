package com.negra.location.utility;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class Validation<T> {

    public static <T> void validateBeans(T t){

        ValidatorFactory factory = javax.validation.Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);
        for (ConstraintViolation<T> constraintViolation : constraintViolations) {
            System.out.println(constraintViolation.getPropertyPath() + " - " +
                    constraintViolation.getMessage());
        }

    }

}
