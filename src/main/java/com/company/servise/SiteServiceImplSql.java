package com.company.servise;

import com.company.domain.Contact;
import com.company.domain.User;
import com.company.repository.DAOContact;
import com.company.repository.DAOUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Onlin on 29.10.2016.
 */
@Component
public class SiteServiceImplSql implements SiteService {

    @Autowired
    private DAOUser daoUser;


    @Autowired
    private DAOContact daoContact;



    public SiteServiceImplSql() {
    }

    @Override
    public User findUserById(Long id) {
        return daoUser.findOne(id);
    }

    @Override
    @Transactional
    @Modifying
    public User addUser(User user) {
        return daoUser.save(user);
    }


    @Override
    @Transactional(readOnly = true)
    public User findUserByLoginAndPassword(String login, String password) {
        return daoUser.findByLoginAndPassword(login, password);
    }

    @Override
    @Transactional
    @Modifying
    public Contact addContact(Contact contact) {
        return daoContact.save(contact);
    }

    @Override
    @Transactional
    public Contact findContactById(Long id) {
        return daoContact.findOne(id);
    }

    @Override
    @Transactional
    public Iterable<Contact> findByUserId(Long userId) {
        return daoContact.findAllByUserId(userId);
    }

    @Override
    @Transactional
    public void deleteContact(Long id) {
        daoContact.delete(id);

    }

    @Override
    public Contact saveContactChanges(Contact contact) {
        return daoContact.save(contact);
    }
}
