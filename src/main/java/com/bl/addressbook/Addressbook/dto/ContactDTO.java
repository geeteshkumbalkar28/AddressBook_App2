package com.bl.addressbook.Addressbook.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.ToString;


@ToString
public class ContactDTO {


  @Pattern(regexp = "^[A-Z][A-Za-z]{1,}$",message = "first Name is invalid(Ex:Geetesh)")
   @NotEmpty(message = "FirstName Cannot be empty")
   public String firstName;


    @Pattern(regexp = "^[A-Z][A-Za-z]{1,}$",message = "Last Name is invalid(Ex:Kumbalkar)")
    @NotEmpty(message = "Last Name Cannot be empty")
    public String lastName;

    @NotEmpty(message = "Address Cannot be empty")
    @Pattern(regexp = "^[A-Z][A-Za-z]{1,}$",message = "Address is invalid(Ex:Sudampuri)")
    public String address;

    @NotEmpty(message = "City Cannot be empty")
    @Pattern(regexp = "^[A-Z][A-Za-z]{1,}$",message = "city is invalid(Ex:Wardha)")
    public String city;

//    @NotEmpty(message = "mobile Cannot be empty")
//    @Pattern(regexp = "^[6-9][0-9]{9}$" ,message = "Mobile is invalid(Ex:9855446624)")
    public Long mobileNumber;
}
