package com.bl.addressbook.Addressbook.model;

import com.bl.addressbook.Addressbook.dto.ContactDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Contacts {
    @Id
    @GeneratedValue
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private Long mobileNumber;

    public Contacts() {}

    public Contacts(ContactDTO contactDTO) {
        this.firstName = contactDTO.firstName;
        this.lastName = contactDTO.lastName;
        this.address = contactDTO.address;
        this.city =contactDTO.city;
        this.mobileNumber = contactDTO.mobileNumber;
    }


}
