package com.cms.test.dto.response;


import com.cms.test.mongodocument.BrandDocument;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BrandInfoResponse {
    private String id;
    private String brandId;
    private String brandName;
    private String brandDescription;

    public BrandInfoResponse(BrandDocument brandDocument){
        this.id = brandDocument.getId();
        this.brandName = brandDocument.getBrandName();
        this.brandId = brandDocument.getBrandId();
        this.brandDescription = brandDocument.getDescription();
    }
}
