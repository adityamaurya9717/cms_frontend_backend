package com.cms.test.mongorepo;

import com.cms.test.mongodocument.CategoryDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDocumentRepo extends MongoRepository<CategoryDocument,String> {
}
