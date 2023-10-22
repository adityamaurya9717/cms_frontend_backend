package com.cms.test.mongorepo;

import com.cms.test.mongodocument.ProductDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDocumentRepo extends MongoRepository<ProductDocument,String> {
}
