package com.cms.test.mongorepo;

import com.cms.test.mongodocument.CounterDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterDocumentRepo extends MongoRepository<CounterDocument,String> {
}
