package com.rayanen.bankAccount.exception;


import com.rayanen.bankAccount.dto.ResponseDto;
import com.rayanen.bankAccount.dto.ResponseException;
import com.rayanen.bankAccount.dto.ResponseStatus;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({Throwable.class})
    public ResponseEntity<ResponseDto> handleAllExceptions(Throwable throwable) {
        String error;
        if (throwable instanceof MethodArgumentNotValidException ) {
            error = ((MethodArgumentNotValidException) throwable).getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        }else if (throwable instanceof HttpMessageNotReadableException)
        {
            error="مقدار وارد شده برای این فیلد صحیح نمیباشد";
        }else if (throwable instanceof DataIntegrityViolationException){
             error="شماره حساب وارد شده موجود میباشد لطفا مجددا تلاش کنید";
        }

        else {
            error = throwable.getMessage();

        }

        ResponseDto responseDto = new ResponseDto(ResponseStatus.Error, null, error, new ResponseException(error));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}