package com.cms.test.dto.request;

import lombok.Data;

@Data
public class GetBrandRequest {

    private String brandId;
    private String brandName;
    private Boolean active;
    private int pageNo=1;
    private int size=20;
}
