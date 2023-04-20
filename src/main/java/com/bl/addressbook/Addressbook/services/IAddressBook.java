package com.bl.addressbook.Addressbook.services;

import com.bl.addressbook.Addressbook.dto.ContactDTO;
import com.bl.addressbook.Addressbook.dto.ResponceDTO;
import com.bl.addressbook.Addressbook.model.Contacts;

import java.util.List;
import java.util.Optional;

public interface IAddressBook {
    public ResponceDTO addContact(ContactDTO contactDTO);
    public Contacts getContactByID(String token);
    public List<Contacts> getAllContact();
    public String deleteContactByID(int id);
    public String deleteAllContact();
    public Contacts updateContactDetailsByID(ContactDTO contactDTO,int id);

    public ResponceDTO registerUser(ContactDTO contactDTO);

    public String verifyAccount(int OTP);

    public String loginUser(int id,String userName,String password);
}
