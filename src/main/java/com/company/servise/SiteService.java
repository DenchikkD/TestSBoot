package com.company.servise;

import com.company.domain.Contact;
import com.company.domain.User;

import java.util.List;

/**
 * Created by Onlin on 29.10.2016.
 */
public interface SiteService  {

    User findUserById(Long id);
    User addUser(User user);

    User findUserByLoginAndPassword(String login, String password);

    Contact addContact(Contact contact);

    Contact findContactById(Long id);

    Iterable<Contact> findByUserId(Long userId);

    void deleteContact(Long id);

    Contact saveContactChanges(Contact contact);


}
