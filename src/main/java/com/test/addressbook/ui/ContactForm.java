package com.test.addressbook.ui;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.test.addressbook.domain.Address;
import com.test.addressbook.domain.PhoneNumber;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.List;
import java.util.Set;

/**
 * Created by Fedir on 11.10.2015.
 */
public class ContactForm extends FormLayout {

    Grid phoneNumberList = new Grid();

    Button save = new Button("Save", this::save);
    Button delete = new Button("Delete ", this::delete);
    Button cancel = new Button("Cancel", this::cancel);
    Button addPhone = new Button("Add Phone Number", this::addPhone);
    Button deletePhone = new Button("Delete Selected Number", this::deletePhone);
    TextArea text = new TextArea("Address");

    Address address;


    BeanFieldGroup<Address> formFieldBindings;

    public ContactForm() {
        configureComponents();
        buildLayout();
    }

    private void configureComponents() {

        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        phoneNumberList.setContainerDataSource(new BeanItemContainer<>(PhoneNumber.class));
        phoneNumberList.removeColumn("address");
        phoneNumberList.removeColumn("id");
        phoneNumberList.getColumn("number").setHeaderCaption("Phone Number");
        phoneNumberList.setEditorEnabled(true);
        setVisible(false);
    }

    private static void validatePhoneNumbers(Set<PhoneNumber> phoneNumbers) throws FieldGroup.CommitException
    {
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

        com.google.i18n.phonenumbers.Phonenumber.PhoneNumber number;

        for (PhoneNumber originalNumber : phoneNumbers) {
            try {
                number = phoneNumberUtil.parse(originalNumber.getNumber(), null);
                phoneNumberUtil.format(number, PhoneNumberUtil.PhoneNumberFormat.E164);
            } catch (NumberParseException e) {
                throw new FieldGroup.CommitException("Invalid phone number:"+ originalNumber.getNumber() + ":" + e.getMessage());
            }
        }
    }

    private void buildLayout() {
        setSizeUndefined();
        setMargin(true);

        HorizontalLayout actions = new HorizontalLayout(save, delete, cancel);
        actions.setSpacing(true);
        actions.setSizeUndefined();
        text.setWidth(50, Unit.EM);
        text.setRows(2);
        addComponents(actions, text);
        addComponents(actions, phoneNumberList);
        addComponents(addPhone, deletePhone);
    }

    public void save(Button.ClickEvent event) {
        try {
            validatePhoneNumbers(address.getPhoneNumbers());
            formFieldBindings.commit();

            getUI().addressService.save(address);

            String msg = String.format("Saved '%s '.",
                    address.getText());
            Notification.show(msg, Notification.Type.TRAY_NOTIFICATION);
            getUI().refreshContacts();
        } catch (FieldGroup.CommitException e) {
            Notification.show(e.getMessage(), Notification.Type.TRAY_NOTIFICATION);
        }
    }

    public void cancel(Button.ClickEvent event) {

        Notification.show("Cancelled", Notification.Type.TRAY_NOTIFICATION);
        getUI().contactList.select(null);

    }

    public void delete(Button.ClickEvent event) {

        getUI().addressService.delete(address);
        Notification.show("Deleted", Notification.Type.TRAY_NOTIFICATION);
        getUI().contactList.select(null);
        getUI().refreshContacts();

    }

    public void addPhone(Button.ClickEvent event) {
        address.getPhoneNumbers().add(new PhoneNumber());
        phoneNumberList.setContainerDataSource(new BeanItemContainer<>(
                PhoneNumber.class, address.getPhoneNumbers()));

    }

    public void deletePhone(Button.ClickEvent event) {
        if (phoneNumberList.getSelectedRow()!=null) {
            address.getPhoneNumbers().remove((PhoneNumber) phoneNumberList.getSelectedRow());
            phoneNumberList.setContainerDataSource(new BeanItemContainer<>(
                    PhoneNumber.class, address.getPhoneNumbers()));
        }
    }

    void edit(Address contact) {
        this.address = contact;
        if(contact != null) {

            formFieldBindings = BeanFieldGroup.bindFieldsBuffered(contact, this);
            phoneNumberList.setContainerDataSource(new BeanItemContainer<>(
                    PhoneNumber.class, contact.getPhoneNumbers()));
            text.focus();
        }
        setVisible(contact != null);
    }

    @Override
    public AddressBookUI getUI() {
        return (AddressBookUI) super.getUI();
    }

}