package com.codewithdurgesg.blog.exceptions;
import java.util.*;
import com.codewithdurgesg.blog.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException ex){
        String message=ex.getMessage();
        ApiResponse api=new ApiResponse(message,false);
        return new ResponseEntity<>(api,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> methodArgumentNotFoundException(MethodArgumentNotValidException ex){
        Map<String,String> map=new HashMap<String,String>();
        ex.getBindingResult().getAllErrors().forEach((e)-> {
            String fieldName=((FieldError)e).getField();
            String message=e.getDefaultMessage();

            map.put(fieldName,message);
        });
        return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse> apiException(ApiException ex){
        String message=ex.getMessage();
        ApiResponse api=new ApiResponse(message,false);
        return new ResponseEntity<>(api,HttpStatus.BAD_REQUEST);
    }

}
