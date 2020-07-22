package app.constraints;

import app.dto.PasswordDTO;
import app.dto.UserDTO;
import app.entity.PasswordResetToken;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class PasswordResetValidator implements ConstraintValidator<PasswordResetMatches,Object> {

    @Override
    public void initialize(PasswordResetMatches constraintAnnotation) { }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        PasswordDTO passwordDTO = (PasswordDTO) o;
        return passwordDTO.getPassword().equals(passwordDTO.getConfirmPassword());
    }


    }
