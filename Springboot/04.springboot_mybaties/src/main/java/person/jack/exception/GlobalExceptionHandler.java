package person.jack.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultExceptionHandler(Exception e){
        e.printStackTrace();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("DealError");
        mv.addObject("mesg", e.getMessage());
        return mv;
    }
}
