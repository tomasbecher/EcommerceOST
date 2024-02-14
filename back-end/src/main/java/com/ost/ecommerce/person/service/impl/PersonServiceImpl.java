package com.ost.ecommerce.person.service.impl;

import com.ost.ecommerce.person.PersonRepository;
import com.ost.ecommerce.person.repository.Person;
import com.ost.ecommerce.person.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person getByIdOrFail(Integer personId) {
        // TODO agregar a ExceptionHandler
        return personRepository.findById(personId).orElseThrow(
                () -> new RuntimeException(
                        "Person not found.")
        );
    }
}
