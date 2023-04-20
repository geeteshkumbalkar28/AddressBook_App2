package com.bl.addressbook.Addressbook.exception;

import com.bl.addressbook.Addressbook.dto.ResponceDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class AddressBookHandler {
   @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponceDTO> handleMethodArgugumentNotValid(MethodArgumentNotValidException exception){
        List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
        List<String> errorMessage = errorList.stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.toList());
        ResponceDTO responceDTO = new ResponceDTO("Exeception while handling RestApi Call",errorMessage);
        return new ResponseEntity<>(responceDTO, HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(AddressBookCustomException.class)
    public ResponseEntity<ResponceDTO> handleAddressBookCustomException(AddressBookCustomException exception){
       ResponceDTO responceDTO=new ResponceDTO("Exception while handle Rest Api Call",exception.getMessage());
       return new ResponseEntity<>(responceDTO,HttpStatus.BAD_REQUEST);
    }
}
