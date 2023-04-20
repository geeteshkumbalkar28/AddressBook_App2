package com.bl.addressbook.Addressbook.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponceDTO {
    private String message;
    private Object data;

    public ResponceDTO(String message,Object data){
        this.message=message;
        this.data=data;
    }
}
