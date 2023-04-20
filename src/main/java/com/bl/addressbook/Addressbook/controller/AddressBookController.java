package com.bl.addressbook.Addressbook.controller;

import com.bl.addressbook.Addressbook.dto.ContactDTO;
import com.bl.addressbook.Addressbook.dto.ResponceDTO;
import com.bl.addressbook.Addressbook.model.Contacts;
//import com.bl.addressbook.Addressbook.repository.ContactsRepo;
import com.bl.addressbook.Addressbook.services.IAddressBook;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/AddressBook")
public class AddressBookController {

    @Autowired
    private IAddressBook iAddressBook;

    @PostMapping("/save")
    public ResponceDTO addContact(@Valid @RequestBody ContactDTO contactDTO){
        return iAddressBook.addContact(contactDTO);
    }

    @GetMapping("/GetByToken")
    public Contacts getContactsById(@RequestHeader String token){
        return iAddressBook.getContactByID(token);
    }
    @GetMapping("/GetAll")
    public List<Contacts> getAllContacts(){
        return iAddressBook.getAllContact();
    }
    @PutMapping("Edit/{id}")
    public Contacts editContactById(@Valid @RequestBody ContactDTO contactDTO, @PathVariable int id){
        return iAddressBook.updateContactDetailsByID(contactDTO,id);
    }
    @DeleteMapping("/Delete/{id}")
    public String deleteContactById(@PathVariable int id){
        return iAddressBook.deleteContactByID(id);
    }
    @DeleteMapping("/DeleteAll")
    public String deleteAllContacts(){
        return iAddressBook.deleteAllContact();
    }
    @PostMapping("/register")
    public ResponceDTO registerUser(@RequestBody ContactDTO contactDTO){
        return iAddressBook.registerUser(contactDTO);
    }
    @GetMapping("/verify/{otp}")
    public String verifyUser(@PathVariable int otp){
        return iAddressBook.verifyAccount(otp);
    }
//    @RequestMapping(value="/login",method = RequestMethod.GET)
    @GetMapping("/login")
    public String login(@RequestParam int id ,@RequestParam String username,@RequestParam String password){
        return iAddressBook.loginUser(id,username,password);
    }


}
