package com.test.addressbook.services;

import com.test.addressbook.domain.Address;
import com.test.addressbook.domain.PhoneNumber;
import com.test.addressbook.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * Created by Fedir on 09.10.2015.
 */
@Service
public class AddressServiceImpl implements AddressService{
    @Autowired
    private AddressRepository addressRepository;

    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    @Override
    @Transactional
    public void save(Address address) {
        for (PhoneNumber phoneNumber : address.getPhoneNumbers()) {
            phoneNumber.setAddress(address);
        }
        addressRepository.save(address);

    }

    @Override
    public void delete(Address address) {
        addressRepository.delete(address);
    }
}
