package com.cms.test.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CategoryResponse {

    private int pageNo;
    private int size;
    private long totalCount;
    private List<CategoryInfoResponse> categoryList = new ArrayList<>();

    public CategoryResponse(int pageNo,long totalcount,List<CategoryInfoResponse> infoResponses){
        this.pageNo = pageNo;
        this.size = infoResponses.size();
        this.totalCount = totalcount;
        this.categoryList = infoResponses;

    }


}
