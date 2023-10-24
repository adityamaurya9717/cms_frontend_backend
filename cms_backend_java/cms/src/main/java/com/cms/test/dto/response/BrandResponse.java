package com.cms.test.dto.response;


import com.cms.test.mongodocument.BrandDocument;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BrandResponse {
    private String id;
    private String brandId;
    private String brandName;

    public BrandResponse(BrandDocument brandDocument){
        this.id = brandDocument.getId();
        this.brandName = brandDocument.getBrandName();
        this.brandId = brandDocument.getBrandId();
    }
}
