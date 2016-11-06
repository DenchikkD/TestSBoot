package com.company.controller;

import com.company.controller.message.MessageContact;
import com.company.controller.message.MessageUser;
import com.company.domain.Contact;
import com.company.domain.User;
import com.company.servise.SiteService;
import com.company.utils.ServerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;


/**
 * Created by Onlin on 29.10.2016.
 */
@Controller
@RequestMapping("/")
public class SiteController extends DispatcherServlet {
    public static final String INDEX_PAGE = "index";
    public static final String AUTHORIZATION_PAGE = "authorization";
    public static final String VIEW_USER_PAGE = "viewUserPage";
    public static final String REGISTRATION_PAGE = "registration";


    @Autowired
    private SiteService siteService;

    @Autowired
    private ServerUtils serverUtils;

    public SiteController() {

    }

    @GetMapping("/doAuthorization")
    public ModelAndView doAuthorization(ModelAndView model, MessageUser messageUser, HttpSession session) {
        model.setViewName(AUTHORIZATION_PAGE);
        return model;
    }

    @GetMapping("/authorization")
    public ModelAndView authorization(ModelAndView model, MessageUser messageUser, HttpSession session) {
        model.setViewName(VIEW_USER_PAGE);
        if (!session.isNew()) {
            if (siteService.findUserByLoginAndPassword(messageUser.getLogin(), messageUser.getPassword()) != null) {
                session.setAttribute("user_presence", siteService.findUserByLoginAndPassword(messageUser.getLogin(), messageUser.getPassword()));
                session.setMaxInactiveInterval(1800);
                User user = (User) session.getAttribute("user_presence");
                System.out.println(user.getContacts());
                addObjectToModel(model,user);
            } else {
                model.setViewName(REGISTRATION_PAGE);
            }
        } else {
            model.setViewName(AUTHORIZATION_PAGE);
        }
        return model;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/registration")
    public ModelAndView registrationUser(ModelAndView model, MessageUser messageUser) {
        String viewName = AUTHORIZATION_PAGE;
        if (!serverUtils.checkParameter(messageUser.getFullName(), null, true)) {
            model.addObject("responseMessage", "Full name does not comply");
            System.out.println("This");
            viewName = REGISTRATION_PAGE;
        }
        if (!serverUtils.checkParameter(messageUser.getLogin(), null, true)) {
            model.addObject("responseMessage", "Login does not comply");
            viewName = REGISTRATION_PAGE;
        }
        if (!serverUtils.checkParameter(messageUser.getPassword(), null, true)) {
            model.addObject("responseMessage", "Password does not comply");
            viewName = REGISTRATION_PAGE;
        }
        if (viewName.equals(AUTHORIZATION_PAGE)) {
            User user = siteService.findUserByLoginAndPassword(messageUser.getLogin(), messageUser.getPassword());
            if (user != null) {
                model.addObject("responseMessage", "A user already exists");
                viewName = REGISTRATION_PAGE;
            } else {
                siteService.addUser(new User(messageUser.getLogin(), messageUser.getPassword(), messageUser.getFullName()));
            }
        }
        model.setViewName(viewName);
        return model;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/add")
    public ModelAndView add(ModelAndView model, MessageContact contact, HttpSession session) {
        if (checkSession(model, session)) {
            User user = (User) session.getAttribute("user_presence");
            model.setViewName(VIEW_USER_PAGE);
            siteService.addContact(new Contact(contact.getSurname(), contact.getName(),
                    contact.getPatronymic(), contact.getPhone_number(), contact.getHome_telephone(),
                    contact.getEmail(), contact.getAddress(), user));
            user = siteService.findUserById(user.getId());
            session.setAttribute("user_presence", user);
            addObjectToModel(model,user);
        }
        return model;
    }

    private boolean checkSession(ModelAndView model, HttpSession session) {
        if (session.getAttribute("user_presence") == null) {
            model.setViewName(INDEX_PAGE);
            return false;
        }
        return true;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/makeChanges")
    public ModelAndView makeChanges(ModelAndView model, HttpSession session, HttpServletRequest request, MessageContact contact) {
        if (checkSession(model, session)) {
            User user = (User) session.getAttribute("user_presence");
            if ("delete".equals(contact.getDelete())) {
                siteService.deleteContact(contact.getId());
            } else {
                siteService.saveContactChanges(new Contact(contact.getId(), contact.getSurname(), contact.getName(),
                        contact.getPatronymic(), contact.getPhone_number(), contact.getHome_telephone(),
                        contact.getEmail(), contact.getAddress(), user));
            }
            user = siteService.findUserById(user.getId());
            session.setAttribute("user_presence", user);
           addObjectToModel(model,user);
            model.setViewName(VIEW_USER_PAGE);
        }

        return model;
    }


    @RequestMapping(value = "/exit", method = RequestMethod.GET)
    public ModelAndView exit(ModelAndView model, HttpSession session, MessageUser messageUser) {

        if (checkSession(model, session)) {
            model.setViewName(AUTHORIZATION_PAGE);

            session.invalidate();
        }
        return model;
    }
private void addObjectToModel(ModelAndView model, User user){
    model.addObject("getContact", user.getContacts());
    model.addObject("sizeContactList", user.getContacts().size());
    model.addObject("userLogin", user.getLogin());
    model.addObject("userFullName", user.getFullName());
}

}
