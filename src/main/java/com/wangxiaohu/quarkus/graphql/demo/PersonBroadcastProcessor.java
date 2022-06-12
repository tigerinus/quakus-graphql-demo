package com.wangxiaohu.quarkus.graphql.demo;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import com.wangxiaohu.quarkus.graphql.demo.model.Person;

import io.smallrye.mutiny.operators.multi.processors.BroadcastProcessor;

@ApplicationScoped
public class PersonBroadcastProcessor {

    private static final BroadcastProcessor<Person> PERSON_BROADCAST_PROCESSOR = BroadcastProcessor.create();

    @Produces
    BroadcastProcessor<Person> personBroadcastProcessor() {
        return PERSON_BROADCAST_PROCESSOR;
    }
}
