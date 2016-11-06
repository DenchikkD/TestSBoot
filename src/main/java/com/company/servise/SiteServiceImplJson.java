package com.company.servise;

import com.company.cofiguration.AppConfig;
import com.company.domain.Contact;
import com.company.domain.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Pack200;

/**
 * Created by Onlin on 05.11.2016.
 */
public class SiteServiceImplJson implements SiteService {

    private ObjectMapper mapper;
    private Map<Long, User> usersMap;
    private Long idCountUser;
    private Long idCountContact;
    private File file;

    public SiteServiceImplJson() {

        mapper = new ObjectMapper();
        idCountUser = 0l;
        idCountContact = 0l;
        usersMap = new HashMap<>();
        try {
            file = new File(AppConfig.fileRootName);
            if (file.exists()) {
                HashMap<Long, User> saveMap = mapper.readValue(file, new TypeReference<HashMap<Long, User>>() {
                });
                usersMap = saveMap;
                for (User user : usersMap.values()) {
                    if (idCountUser < user.getId()) {
                        idCountUser = user.getId();
                    }
                    for (Contact contact : user.getContacts()) {
                        if (idCountContact < contact.getId()) {
                            idCountContact = contact.getId();
                        }
                    }
                }
            } else {
                System.out.println(file.createNewFile());
                addUser(new User("admin", "admin", "admin"));
                usersMap = new HashMap<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @Override
    public User findUserById(Long id) {
        if (usersMap != null && !usersMap.isEmpty()) {
            for (Long key : usersMap.keySet()) {
                if (key == id) {
                    return usersMap.get(key);
                }
            }
        }
        return null;
    }


    @Override
    public User addUser(User user) {
        idCountUser++;
        user.setId(idCountUser);
        System.out.println(user);
        usersMap.put(idCountUser, user);
        setFromJsonUserMap();
        return user;
    }

    @Override
    public User findUserByLoginAndPassword(String login, String password) {
        usersMap = getUserMapFromJson();
        System.out.println(usersMap);
        if (usersMap != null && !usersMap.isEmpty()) {
            for (User user : usersMap.values()) {
                if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                    return user;
                }
            }
        }
        return null;
    }

    @Override
    public Contact addContact(Contact contact) {
        if (usersMap != null && !usersMap.isEmpty()) {
            idCountContact++;
            contact.setId(idCountContact);
            usersMap.get(contact.getUser().getId()).getContacts().add(contact);
            setFromJsonUserMap();
            return contact;
        }
        return null;
    }

    @Override
    public Contact findContactById(Long id) {
        usersMap = getUserMapFromJson();
        if (usersMap != null && !usersMap.isEmpty()) {
            for (User user : usersMap.values()) {
                for (Contact contact : user.getContacts()) {
                    if (contact.getId().equals(id)) {
                        return contact;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Iterable<Contact> findByUserId(Long userId) {
        usersMap = getUserMapFromJson();
        return usersMap.get(userId).getContacts();
    }

    @Override
    public void deleteContact(Long id) {
        usersMap = getUserMapFromJson();
        if (usersMap != null && !usersMap.isEmpty()) {
            for (User user : usersMap.values()) {
                for (Contact contact : user.getContacts()) {
                    if (contact.getId().equals(id)) {
                        user.getContacts().remove(contact);
                    }
                }
            }
        }
    }

    @Override
    public Contact saveContactChanges(Contact changedContact) {
        Contact teamContact = null;
        usersMap = getUserMapFromJson();
        if (usersMap != null && !usersMap.isEmpty()) {
            for (Contact contact : usersMap.get(changedContact.getUser().getId()).getContacts()) {
                if (contact.getId().equals(changedContact.getId())) {
                    contact.marge(changedContact);
                    teamContact = contact;
                }
            }
        }
        setFromJsonUserMap();
        return teamContact;
    }

    private HashMap<Long, User> getUserMapFromJson() {
        try {
            return mapper.readValue(file, new TypeReference<HashMap<Long, User>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setFromJsonUserMap() {
        try {
            mapper.writeValue(file, usersMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
