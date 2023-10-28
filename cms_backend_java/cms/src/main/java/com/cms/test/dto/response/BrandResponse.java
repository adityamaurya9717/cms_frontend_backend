package com.cms.test.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BrandResponse {

    private int pageNo;
    private int size;
    private long totalCount;
    private List<BrandInfoResponse> brandList;

    public BrandResponse(int pageNo,long totalCount,List<BrandInfoResponse> brandResponses){
        this.pageNo = pageNo;
        this.size = brandResponses.size();
        this.brandList = brandResponses;
        this.totalCount = totalCount;
    }


}
