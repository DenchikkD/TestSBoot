package com.company.controller.message;

import com.company.domain.User;

/**
 * Created by Onlin on 30.10.2016.
 */
public class MessageContact {
    private Long id;
    private String surname;
    private String name;
    private String patronymic;
    private String phone_number;
    private String home_telephone;
    private String address;
    private String email;

    private User user;
    private Long chooseContact;
    private String add;
    private String delete;

    public MessageContact() {
    }

    public MessageContact(String surname, String name, String patronymic, String phone_number, String home_telephone,  String email, String address, User user, Long id, Long chooseContact) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.phone_number = phone_number;
        this.home_telephone = home_telephone;
        this.address = address;
        this.email = email;
        this.user = user;
        this.id = id;
        this.chooseContact = chooseContact;
    }

    public String getDelete() {
        return delete;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getHome_telephone() {
        return home_telephone;
    }

    public void setHome_telephone(String home_telephone) {
        this.home_telephone = home_telephone;
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

      public Long getChooseContact() {
        return chooseContact;
    }

    public void setChooseContact(Long chooseContact) {
        this.chooseContact = chooseContact;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MessageContact{" +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", home_telephone='" + home_telephone + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", user=" + user +
                ", chooseContact=" + chooseContact +
                ", id=" + id +
                '}';
    }


}


