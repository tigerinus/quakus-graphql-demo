package com.wangxiaohu.quarkus.graphql.demo.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import com.wangxiaohu.quarkus.graphql.demo.model.Person;

@ApplicationScoped
public class PersonService {
    private final Map<Integer, Person> _personMap;

    public PersonService() {
        _personMap = new HashMap<>();
    }

    public Collection<Person> getAllPeople() {
        return _personMap.values();
    }

    public Person getPerson(int id) {
        return _personMap.get(id);
    }

    public Person createPerson(String firstName, String lastName) {
        Integer id = _personMap.keySet().stream().max(Integer::compare).orElse(0) + 1;
        Person person = new Person(id, firstName, lastName);
        _personMap.put(id, person);
        return person;
    }
}
