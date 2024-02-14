package com.ost.ecommerce.person.service;

import com.ost.ecommerce.person.repository.Person;

public interface PersonService {

    Person save(Person person);
    Person getByIdOrFail(Integer personId);
}
