package com.raoulduke.crypto.fivedaysincloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Component
public class MongoCounter {

    private MongoOperations mongoOperations;
    @Autowired
    public MongoCounter(MongoOperations mongoOperations){
        this.mongoOperations = mongoOperations;
    }

    public int generateSequence(String seqName) {
        IdSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                IdSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }
    public int resetSequence(String seqName) {
        IdSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().set("seq",1), options().returnNew(true).upsert(true),
                IdSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }

}
