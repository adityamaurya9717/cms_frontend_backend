package com.cms.test.dto.response;


import com.cms.test.mongodocument.BrandDocument;
import com.cms.test.mongodocument.CategoryDocument;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
public class BrandInfoResponse {
    private String id;
    private String brandId;
    private String brandName;
    private String brandDescription;

    public BrandInfoResponse(BrandDocument brandDocument) {
        this.id = brandDocument.getId();
        this.brandName = brandDocument.getBrandName();
        this.brandId = brandDocument.getBrandId();
        this.brandDescription = brandDocument.getDescription();
    }

}
