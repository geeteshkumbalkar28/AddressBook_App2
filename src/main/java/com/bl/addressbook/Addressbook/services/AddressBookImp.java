package com.bl.addressbook.Addressbook.services;

import com.bl.addressbook.Addressbook.dto.ContactDTO;
import com.bl.addressbook.Addressbook.dto.ResponceDTO;
import com.bl.addressbook.Addressbook.exception.AddressBookCustomException;
import com.bl.addressbook.Addressbook.model.Contacts;
//import com.bl.addressbook.Addressbook.repository.ContactsRepo;
import com.bl.addressbook.Addressbook.repository.ContactRepo;
import com.bl.addressbook.Addressbook.util.EmailService;
import com.bl.addressbook.Addressbook.util.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AddressBookImp implements IAddressBook{
    private LinkedList<Contacts> contactList = new LinkedList<>();
    private Contacts registerContact;
    private int otp;
    @Autowired
    private EmailService emailService;
    @Autowired
    private JWTToken jwtToken;
    @Autowired
    private ContactRepo contactRepo;
    @Override
    public ResponceDTO addContact(ContactDTO contactDTO) {
        Contacts contacts = new Contacts(contactDTO);
//        contactList.add(contacts);
        contactRepo.save(contacts);
        String token =jwtToken.createToken(contacts.getId());
        emailService.sendEmail(contactDTO.getEmail(),"Data Added Sucessfully",
                "Hey..."+contacts.getFirstName()+"\nyou have been sucessfully add data\n\n"+contacts);
        return new ResponceDTO(token,contacts);
    }
    @Override
    public ResponceDTO registerUser(ContactDTO contactDTO) {
        Random random = new Random();
        registerContact = new Contacts(contactDTO);

        otp = (random.nextInt(9000)+999);
//        String token =jwtToken.createToken(registerContact.getId());
        emailService.sendEmail(contactDTO.getEmail(),"Varify Your Accout",
                "Hey... "+(registerContact.getFirstName()+" "+registerContact.getLastName())+"\n\n YOUR OTP IS :=> "+otp);
        return new ResponceDTO("Inforamtion",registerContact);


    }

    @Override
    public String verifyAccount(int OTP) {
        if(otp==OTP){
            Contacts userData = contactRepo.save(registerContact);

            String token =jwtToken.createToken(registerContact.getId());
            emailService.sendEmail(registerContact.getEmail(),"Account verified successful",
                    "Hey... "+(registerContact.getFirstName()+" "+registerContact.getLastName())+"\n\n Account verified successful "+
                            "\n\n"+"********** Your UserName is :=>"+registerContact.getUserName()+
                            "\n\n"+"********** Your Id is :=>"+userData.getId()+
                                "**********\n Your Email ID : "+registerContact.getEmail()+"\n Mobile Number : "+registerContact.getMobileNumber());
            otp=89899898;
            registerContact=null;
            return "Account Register successfully \n Token :"+token;
        }

        return "Invlid Otp";
    }

    @Override
    public String loginUser(int id,String userName,String password) {
        Optional<Contacts> userData = contactRepo.findById(id);
        if(userData.isPresent()){
            if((userData.get().getUserName()).equals(userName)){
                if((userData.get().getPassword()).equals(password)){
                       return "************ Login successfull ****** ";
                }else{
                    return "!!!!!!!!!!!! Invalid Password!!!!!!!!!!!!!!!";
                }

            }else{
                return "!!!!!!!!!!!! Invalid User Name!!!!!!!!!!!!!!";
            }

        }
        return "!!!!!!! Invalid User Id!!!!!!!!!!!!";

    }

    @Override
    public Contacts getContactByID(String token) {
        int id = jwtToken.decodeToken(token);
        return contactRepo.findById(id).orElseThrow(() -> new AddressBookCustomException("Contaxt not found with id : "+id));

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
