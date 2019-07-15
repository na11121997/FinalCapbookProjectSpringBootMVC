package com.cg.capbook.aspect;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.cg.capbook.exceptions.EmailAlreadyRegisteredException;
import com.cg.capbook.exceptions.InvalidEmailIdException;
import com.cg.capbook.exceptions.UserNotFriendException;

@ControllerAdvice(basePackages= {"com.cg.capbook.controllers"})
public class CapBookExceptionAspect {
	@ExceptionHandler(EmailAlreadyRegisteredException.class)
	public ModelAndView handleEmailAlreadyRegisteredException(Exception e) {
		return new ModelAndView("errorPage", "errorMessage", e.getMessage());
	}
	@ExceptionHandler(InvalidEmailIdException.class)
	public ModelAndView InvalidEmailIdException(Exception e) {
		return new ModelAndView("errorPage", "errorMessage", e.getMessage());
	}
	@ExceptionHandler(UserNotFriendException.class)
	public ModelAndView UserNotFriendException(Exception e) {
		return new ModelAndView("errorPage", "errorMessage", e.getMessage());
	}

}