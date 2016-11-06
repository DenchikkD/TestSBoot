package com.company.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Onlin on 30.10.2016.
 */
@Entity
@Table(name = "contacts")
public class Contact implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;


    @Column(name = "surname")
    private String surname;

    @Column(name = "name")
    private String name;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "home_telephone")
    private String homeTelephone;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Contact() {
    }

    public Contact(String surname, String name, String patronymic, String phoneNumber, String homeTelephone, String email, String address, User user) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.phoneNumber = phoneNumber;
        this.homeTelephone = homeTelephone;
        this.email = email;
        this.address = address;
        this.user = user;
    }

    public Contact(String surname, String name, String patronymic, String phoneNumber, String homeTelephone, String email, String address) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.phoneNumber = phoneNumber;
        this.homeTelephone = homeTelephone;
        this.address = address;
        this.email = email;

    }

    public Contact(Long id, String surname, String name, String patronymic, String phoneNumber, String homeTelephone,String email, String address,  User user) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.phoneNumber = phoneNumber;
        this.homeTelephone = homeTelephone;
        this.address = address;
        this.email = email;
        this.user = user;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHomeTelephone() {
        return homeTelephone;
    }

    public void setHomeTelephone(String home_telephone) {
        this.homeTelephone = home_telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", phone_number='" + phoneNumber + '\'' +
                ", home_telephone='" + homeTelephone + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", userId=" + user +
                '}';
    }

    public void marge(Contact contact){
        if (contact!=null){
            if (!this.surname.equals(contact.getSurname())){
                this.surname=contact.getSurname();
            }
            if (!this.name.equals(contact.getName())){
                this.name=contact.getName();
            }
            if (!this.patronymic.equals(contact.getPatronymic())){
                this.patronymic=contact.getPatronymic();
            }
            if (!this.phoneNumber.equals(contact.getPhoneNumber())){
                this.phoneNumber=contact.getPhoneNumber();
            }
            if (!this.homeTelephone.equals(contact.getHomeTelephone())){
                this.homeTelephone=contact.getHomeTelephone();
            }
            if (!this.address.equals(contact.getAddress())){
                this.address=contact.getAddress();
            }
            if (!this.email.equals(contact.getEmail())){
                this.email=contact.getEmail();
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        return id != null ? id.equals(contact.id) : contact.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
