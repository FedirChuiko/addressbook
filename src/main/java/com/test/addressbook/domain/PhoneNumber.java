package com.test.addressbook.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Fedir on 10.10.2015.
 */
@Entity
@Table( uniqueConstraints=
@UniqueConstraint(columnNames={"number"}))
public class PhoneNumber implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "number", length = 20, nullable = false)
    private String number;

    @ManyToOne
    @JoinColumn(name = "Address_id", nullable = false)
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
