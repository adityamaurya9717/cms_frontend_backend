package com.cms.test.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetCustomerRequest {

    private String name;
    private String email;
    private int pageNo=1;
    private int size=10;

}
