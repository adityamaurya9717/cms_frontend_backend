package com.cms.test.utils;

import com.cms.test.mongodocument.CounterDocument;
import com.cms.test.mongorepo.CounterDocumentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CategoryUtil {

    @Autowired
    private CounterDocumentRepo counterDocumentRepo;
    public synchronized Long CategoryIdMaker(){
        List<CounterDocument>  documents  = counterDocumentRepo.findAll();
        if(documents.size()==0){
            CounterDocument counterDocument = new CounterDocument();
            counterDocument.setCounter(100l);
            counterDocumentRepo.save(counterDocument);
            return counterDocument.getCounter();
        }
        else{
         Optional<CounterDocument> optional = documents.stream().findFirst();
         CounterDocument counterDocument = optional.get();
         Long id = counterDocument.getCounter();
         counterDocument.setCounter(counterDocument.getCounter()+1);
         counterDocumentRepo.save(counterDocument);
         return id;

        }
    }
}
