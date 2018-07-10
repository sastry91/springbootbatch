package com.sastry.spring.bootbatch.processor;

import com.sastry.spring.bootbatch.model.Person;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;

public class PersonProcessor implements ItemProcessor<Person, Person> {


    @Override
    public Person process(Person person) throws Exception {
        person.setFatherName(person.getFatherName().toUpperCase());
        person.setFirstName(person.getFirstName().toUpperCase());
        return person;
    }
}
