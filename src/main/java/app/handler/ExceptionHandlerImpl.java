package app.handler;

import app.exception.UserAlreadyExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerImpl {

    @ExceptionHandler({UserAlreadyExistException.class})
    public String handleUserAlreadyExist(final UserAlreadyExistException ex) {
        return "redirect:/registration?emailError="+ex.getMessage();
    }


}