package com.bl.addressbook.Addressbook.services;

import com.bl.addressbook.Addressbook.dto.ContactDTO;
import com.bl.addressbook.Addressbook.model.Contacts;

import java.util.List;
import java.util.Optional;

public interface IAddressBook {
    public Contacts addContact(ContactDTO contactDTO);
    public Optional<Contacts> getContactByID(int id);
    public List<Contacts> getAllContact();
    public String deleteContactByID(int id);
    public String deleteAllContact();
    public Contacts updateContactDetailsByID(ContactDTO contactDTO,int id);

}
