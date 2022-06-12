package com.wangxiaohu.quarkus.graphql.demo;

import java.util.Collection;

import javax.inject.Inject;

import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;

import com.wangxiaohu.quarkus.graphql.demo.model.Person;
import com.wangxiaohu.quarkus.graphql.demo.service.PersonService;

import io.quarkus.logging.Log;
import io.smallrye.graphql.api.Subscription;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.operators.multi.processors.BroadcastProcessor;

@GraphQLApi
public class PersonResource {

    @Inject
    private BroadcastProcessor<Person> _personBroadcastProcessor;

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

        Log.info("signaling the person created...");
        _personBroadcastProcessor.onNext(person);
        Log.info("signaled the person created.");

        return person;
    }

    @Subscription("personCreated")
    public Multi<Person> subscribeToPersonCreation() {
        Log.info("subscribeToPersonCreation");
        return _personBroadcastProcessor;
    }
}
