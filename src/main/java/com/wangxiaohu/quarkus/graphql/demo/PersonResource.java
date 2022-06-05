package com.wangxiaohu.quarkus.graphql.demo;

import java.util.Collection;

import javax.inject.Inject;

import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;

import com.wangxiaohu.quarkus.graphql.demo.model.Person;
import com.wangxiaohu.quarkus.graphql.demo.service.PersonService;

import io.smallrye.graphql.api.Subscription;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.operators.multi.processors.BroadcastProcessor;

@GraphQLApi
public class PersonResource {

    BroadcastProcessor<Person> personBroadcastProcessor = BroadcastProcessor.create();

    @Inject
    PersonService _personService;

    @Query("getAllPeople")
    public Collection<Person> getAllPeople() {
        return _personService.getAllPeople();
    }

    @Query("getPersonById")
    public Person getPerson(int id) {
        return _personService.getPerson(id);
    }

    @Mutation("createPerson")
    public Person createPerson(String firstName, String lastName) {
        Person person = _personService.createPerson(firstName, lastName);

        personBroadcastProcessor.onNext(person);

        return person;
    }

    @Subscription("personCreated")
    public Multi<Person> subscribeToPersonCreation() {
        return personBroadcastProcessor;
    }
}
