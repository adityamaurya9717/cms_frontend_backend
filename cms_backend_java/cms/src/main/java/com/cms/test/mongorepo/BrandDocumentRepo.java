package com.cms.test.mongorepo;

import com.cms.test.mongodocument.BrandDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandDocumentRepo extends MongoRepository<BrandDocument,String> {
}
