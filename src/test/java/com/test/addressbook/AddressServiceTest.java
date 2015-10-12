package com.test.addressbook;

import com.test.addressbook.domain.Address;
import com.test.addressbook.domain.PhoneNumber;
import com.test.addressbook.repositories.AddressRepository;
import com.test.addressbook.services.AddressService;
import com.test.addressbook.services.AddressServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Fedir on 9.10.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = SpringApplicationContextLoader.class)
public class AddressServiceTest {

    @Configuration
    static class AddressServiceTestContextConfiguration {
        @Bean
        public AddressService addressService() {
            return new AddressServiceImpl();
        }

        @Bean
        public AddressRepository addressRepository() {
            return Mockito.mock(AddressRepository.class);
        }
    }


    @Autowired
    private AddressService addressService;
    @Autowired
    private AddressRepository addressRepository;

    @Before
    public void setup() {
        Address address1 = new Address();
        address1.setText("address1");
        PhoneNumber phone1 = new PhoneNumber();
        phone1.setNumber("phone1");
        PhoneNumber phone2 = new PhoneNumber();
        phone2.setNumber("phone2");
        Set<PhoneNumber> numbers = new HashSet<PhoneNumber>();
        numbers.add(phone1);
        numbers.add(phone2);
        address1.setPhoneNumbers(numbers);

        Address address2 = new Address();
        address2.setText("address2");
        ArrayList<Address> list= new ArrayList<Address>();

        list.add(address1);
        list.add(address2);
        Mockito.when(addressRepository.findAll()).thenReturn(list);
    }
    @Test()
    public void testGetAll()  {
        List<Address> list = addressService.getAll();
        Assert.assertEquals("address1", list.get(0).getText());
        Assert.assertEquals("address2", list.get(1).getText());
    }
}
