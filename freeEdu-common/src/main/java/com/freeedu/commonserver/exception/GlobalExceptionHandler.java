package com.freeedu.commonserver.exception;

import com.freeedu.commonserver.entity.Resp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author: fuzf
 * @Date: 2020/9/14 14:10
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public Resp businessException(BusinessException e) {
        return Resp.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler({
            MethodArgumentTypeMismatchException.class})
    public Resp handle(MethodArgumentTypeMismatchException e) {
        String message = e.getName() + "参数类型错误!需要" + e.getRequiredType().getName() + "类型,请检查后提交!";
        return Resp.error(message);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Resp handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
//        log.error("", ex);
//        BindingResult bindingResult = ex.getBindingResult();
//        if (bindingResult != null) {
//            List<ObjectError> errorList = bindingResult.getAllErrors();
//            if (!CollectionUtils.isEmpty(errorList)) {
//                return Resp.error(errorList.get(0).getObjectName() + " error");
//            }
//        }
//        return Resp.error("参数错误");
//    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {BindException.class, ValidationException.class, MethodArgumentNotValidException.class})
    public Resp handleParameterVerificationException(Exception e) {

        AtomicReference<String> error = new AtomicReference<>("");
        log.warn("Exception: {}", e.getMessage());
        if (e instanceof BindException) {
            BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
            List<ObjectError> errors = bindingResult.getAllErrors();
            errors.forEach(p -> {
                FieldError fieldError = (FieldError) p;
                log.error("Invalid Parameter : object - {},field - {},errorMessage - {}", fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage());
                error.set(error + (fieldError.getField() + fieldError.getDefaultMessage()));
            });
            return Resp.error(error.toString());

        } else if (e instanceof ConstraintViolationException) {
            if (e.getMessage() != null) {
                return Resp.error(e.getMessage());
            }
        } else {
            return Resp.error(e.getMessage());
        }
        return Resp.error("Invalid Parameter");
    }

    /**
     * 无效的请求,请求参数不对
     */
    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.OK)
    public Resp handleNotFoundException(NumberFormatException e) {
        log.warn("NumberFormatException {}", e.getMessage());
        return Resp.error("参数个数错误" + e.getMessage());
    }

    @ExceptionHandler({SQLException.class})
    public Resp handle(SQLException e) {
//        ULogger.error("SQL异常", new Object[]{e});

        return Resp.error("DB异常:" + e.getMessage());
    }

  @ExceptionHandler({IllegalStateException.class})
    public Resp illegalStateException(IllegalStateException e) {
//        ULogger.error("SQL异常", new Object[]{e});
        return Resp.error("参数异常:" + e.getMessage());
    }

    @ExceptionHandler({HttpMessageNotReadableException.class,
            HttpMessageConversionException.class})
    public Resp illegalStateException(HttpMessageNotReadableException e) {

//        ULogger.error("SQL异常", new Object[]{e});
        return Resp.error("参数转化异常:" + e.getMessage());
    }


    @ExceptionHandler(Exception.class)
    public Resp customException(Exception e) {
        return Resp.error(e.getMessage());
    }
}
