package com.cms.test.dto.request;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ProductAttributes {

    private String productDetailDescription;
    private List<Map> colors ;
}
