package com.test.addressbook.repositories;

import com.test.addressbook.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Fedir on 10.10.2015.
 */

public interface AddressRepository extends JpaRepository <Address,Integer>{
}
