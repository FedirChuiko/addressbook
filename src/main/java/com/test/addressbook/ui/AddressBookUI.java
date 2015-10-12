package com.test.addressbook.ui;

import com.test.addressbook.domain.Address;
import com.test.addressbook.services.AddressService;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.annotation.WebServlet;

/**
 * Created by Fedir on 09.10.2015.
 */

@SpringUI
public class AddressBookUI extends UI {
    @Autowired
    public void setAddressService(AddressService addressService) {
        this.addressService = addressService;
    }

    AddressService addressService;

    ContactForm contactForm = new ContactForm();

    Grid contactList = new Grid();
    Button newContact = new Button("New address");

    @Override
    protected void init(VaadinRequest request) {
        configureComponents();
        buildLayout();
    }


    private void configureComponents() {

        newContact.addClickListener(e -> contactForm.edit(new Address()));
        contactList.setContainerDataSource(new BeanItemContainer<>(Address.class));
        contactList.removeColumn("phoneNumbers");
        contactList.removeColumn("id");
        contactList.getColumn("text").setHeaderCaption("Address");
        contactList.setSelectionMode(Grid.SelectionMode.SINGLE);
        contactList.addSelectionListener(e
                -> contactForm.edit((Address) contactList.getSelectedRow()));
        refreshContacts();
    }


    private void buildLayout() {
        HorizontalLayout actions = new HorizontalLayout(newContact);
        actions.setWidth("100%");


        VerticalLayout left = new VerticalLayout(actions, contactList);
        left.setSizeFull();
        contactList.setSizeFull();
        left.setExpandRatio(contactList, 1);


        HorizontalLayout mainLayout = new HorizontalLayout(left, contactForm);
        mainLayout.setSizeFull();
        mainLayout.setExpandRatio(left, 1);

        setContent(mainLayout);

    }

    void refreshContacts() {
        contactList.setContainerDataSource(new BeanItemContainer<>(
                Address.class, addressService.getAll()));
        contactForm.setVisible(false);
    }




}