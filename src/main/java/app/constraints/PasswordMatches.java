package app.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
@Documented
public @interface PasswordMatches {
    String message() default "Passwords are not the same";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
