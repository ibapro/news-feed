package app.handler;

import app.exception.TokenExpiredException;
import app.exception.UserAlreadyExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerImpl {

    @ExceptionHandler({UserAlreadyExistException.class})
    public String handleUserAlreadyExist(final UserAlreadyExistException ex) {
        return "redirect:/registration?emailError="+ex.getMessage();
    }

    @ExceptionHandler({TokenExpiredException.class})
    public String handleTokenExpired(final TokenExpiredException ex) {
        return "redirect:/index?tokenError="+ex.getMessage();
    }


}
