package com.test.addressbook.services;

import com.test.addressbook.domain.Address;

import java.util.List;

/**
 * Created by Fedir on 09.10.2015.
 */

public interface AddressService {
    List<Address> getAll();
    void save(Address address);
    void delete(Address address);
}
