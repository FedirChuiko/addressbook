package com.test.addressbook.repositories;

import com.test.addressbook.domain.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Fedir on 10.10.2015.
 */
@Repository
public interface PhoneNumberRepository extends JpaRepository <PhoneNumber,Integer>{
}
