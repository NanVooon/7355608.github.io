package com.ld.poetize.exception;

import com.ld.poetize.utils.web.R;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author zuosy
 * @Date 2024/1/24 19:38
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局捕获断言异常
     * @param exception
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public R<Integer> validatedIllegalArgumentException(IllegalArgumentException exception){
        return R.failForMsg(exception.getMessage());
    }

    /**
     * validation校验异常信息捕获
     * @param exception
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R<String> methodArgumentNotValidException(MethodArgumentNotValidException exception){
        BindingResult bindingResult = exception.getBindingResult();
        if (bindingResult.hasErrors()){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            Set<String> messages = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.toSet());
            return R.failForMsg(String.join(",", messages));
        }
        return R.failForMsg("入参异常");
    }
}
