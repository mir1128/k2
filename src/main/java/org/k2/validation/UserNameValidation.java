package org.k2.validation;

import javax.validation.Payload;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@NotNull
@Size(min = 3, max = 15)
public @interface UserNameValidation {
    String message() default "not a valid user name.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

