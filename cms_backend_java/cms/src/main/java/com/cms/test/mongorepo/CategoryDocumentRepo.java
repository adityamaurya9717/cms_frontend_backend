package com.cms.test.mongorepo;

import com.cms.test.mongodocument.CategoryDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryDocumentRepo extends MongoRepository<CategoryDocument,String> {
    @Query(value = "{categoryLevel: ?0 }")
    List<CategoryDocument> getLevelThreeCategory(int levelid);
}
