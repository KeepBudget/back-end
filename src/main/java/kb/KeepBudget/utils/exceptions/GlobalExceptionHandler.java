package kb.KeepBudget.utils.exceptions;

import kb.KeepBudget.utils.ApiUtils;
import kb.KeepBudget.utils.ApiUtils.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 지원하지 않은 HTTP method 호출 할 경우 발생
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ApiResult handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
        return ApiUtils.error(e.getMethod(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * 필요한 Request body element을 입력하지 않을 경우
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResult handleHttpMessageNotReadableException(HttpMessageNotReadableException e){
        log.error("HttpMessageNotReadableException = {}", e.getMessage());
        return ApiUtils.error(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * 필요한 Request body element을 입력하지 않을 경우
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        log.error("MethodArgumentNotValidException = {}", e.getMessage());
        Map<String, String> errorMap = new HashMap<>();

        e.getFieldErrors().forEach(fieldError -> {
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        });

        return ApiUtils.error(errorMap, HttpStatus.BAD_REQUEST);
    }

    /*
    * 닉네임 중복일 경우
    * */
    @ExceptionHandler(DuplicateNicknameException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiResult handleDuplicateNicknameException(DuplicateNicknameException e){
        log.error("DuplicateNicknameException = {}", e.getMessage());
        return ApiUtils.error(e.getMessage(), HttpStatus.CONFLICT);
    }

    /*
     * District 존재하지 않을 경우
     * */
    @ExceptionHandler(DistrictNotExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResult handleDistrictNotExistException(DistrictNotExistException e){
        log.error("DistrictNotExistException = {}", e.getMessage());
        return ApiUtils.error(e.getMessage(), HttpStatus.BAD_REQUEST);
    }



}
