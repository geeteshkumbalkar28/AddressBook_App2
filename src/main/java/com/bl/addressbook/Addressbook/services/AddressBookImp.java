package com.bl.addressbook.Addressbook.services;

import com.bl.addressbook.Addressbook.dto.ContactDTO;
import com.bl.addressbook.Addressbook.model.Contacts;
//import com.bl.addressbook.Addressbook.repository.ContactsRepo;
import com.bl.addressbook.Addressbook.repository.ContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressBookImp implements IAddressBook{
    LinkedList<Contacts> contactList = new LinkedList<>();

    @Autowired
    private ContactRepo contactRepo;
    @Override
    public Contacts addContact(ContactDTO contactDTO) {
        Contacts contacts = new Contacts(contactDTO);
//        contactList.add(contacts);
     return contactRepo.save(contacts);
    }

    @Override
    public Optional<Contacts> getContactByID(int id) {
        return contactRepo.findById(id);
    }

    @Override
    public List<Contacts> getAllContact() {
        return contactRepo.findAll();
    }

    @Override
    public String deleteContactByID(int id) {
        Optional<Contacts> contacts = contactRepo.findById(id);
        if(contacts.isPresent()){
            contactRepo.deleteById(id);
            return "Contact Delete Succesfully :"+ id;
        }
        return "id invalid";
    }

    @Override
    public String deleteAllContact() {

       contactRepo.deleteAll();
        return "All contact deleted";
    }

    @Override
    public Contacts updateContactDetailsByID(ContactDTO contactDTO,int id) {
        Optional<Contacts> contacts = contactRepo.findById(id);
        if(contacts.isPresent()){
            contacts.get().setFirstName(contactDTO.firstName);
            contacts.get().setLastName(contactDTO.lastName);
            contacts.get().setCity(contactDTO.city);
            contacts.get().setMobileNumber(contactDTO.mobileNumber);
            contacts.get().setAddress(contactDTO.address);

            return contactRepo.save(contacts.get());

        }
        return contacts.get();
    }
}
