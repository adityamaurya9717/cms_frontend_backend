package com.cms.test.dto.response;

import com.cms.test.mongodocument.ProductDocument;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GetProductResponse {

    private int pageNo;
    private int size;
    private long totalCount;
    List<ProductDocument> productList = new ArrayList<>();

    public GetProductResponse(int pageNo,long totalCount,List<ProductDocument> productDocuments){
        this.pageNo = pageNo;
        this.size = productDocuments.size();
        this.totalCount = totalCount;
        this.productList = productDocuments;

    }
}
