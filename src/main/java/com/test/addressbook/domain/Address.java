package com.test.addressbook.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * Created by Fedir on 09.10.2015.
 */
@Entity
@Table(name = "address")
public class Address implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "text", length = 1024, nullable = false)
    private String text;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "address", cascade = {CascadeType.ALL})
    private Set<PhoneNumber> phoneNumbers = new HashSet<PhoneNumber>(0);

    public Set<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Set<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
