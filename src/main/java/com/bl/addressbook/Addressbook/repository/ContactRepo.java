package com.bl.addressbook.Addressbook.repository;

import com.bl.addressbook.Addressbook.model.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepo extends JpaRepository <Contacts,Integer> {
}
