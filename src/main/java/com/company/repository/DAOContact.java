package com.company.repository;

import com.company.domain.Contact;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Repository;
//
import java.math.BigInteger;
import java.util.List;
import java.util.Set;

/**
 * Created by Onlin on 30.10.2016.
 */
@Repository
public interface DAOContact extends CrudRepository<Contact, Long> {

    Set<Contact> findAllByUserId(Long userId);

    Contact findById(Long id);


}
