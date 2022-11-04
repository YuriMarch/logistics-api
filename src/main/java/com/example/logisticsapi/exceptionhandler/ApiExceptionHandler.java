package com.example.logisticsapi.exceptionhandler;

import com.example.logisticsapi.exceptions.BusinessException;
import com.example.logisticsapi.exceptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<ErrorFields> errors = new ArrayList<>();
        for (ObjectError error: ex.getBindingResult().getAllErrors()){
            String name = ((FieldError) error).getField();
            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            errors.add(new ErrorFields(name, message));
        }

        CustomException exception = new CustomException();
        exception.setStatus(status.value());
        exception.setDate(LocalDateTime.now());
        exception.setTitle("One of more invalid fields. Please try again.");
        exception.setErrorFields(errors);

        return this.handleExceptionInternal(ex, exception, headers, status, request);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;

        CustomException exception = new CustomException();
        exception.setStatus(status.value());
        exception.setDate(LocalDateTime.now());
        exception.setTitle(ex.getMessage());

        return handleExceptionInternal(ex, exception, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;

        CustomException exception = new CustomException();
        exception.setStatus(status.value());
        exception.setDate(LocalDateTime.now());
        exception.setTitle(ex.getMessage());

        return handleExceptionInternal(ex, exception, new HttpHeaders(), status, request);
    }
}
