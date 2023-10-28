package com.cms.test.mongodocument;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "category_counter")
public class CounterDocument {

    @MongoId
    private String id;
    @Field(name = "counter")
    private Long counter;
}
