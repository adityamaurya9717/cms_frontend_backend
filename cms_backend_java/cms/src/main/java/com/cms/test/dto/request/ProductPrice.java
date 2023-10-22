package com.cms.test.dto.request;

import lombok.Data;

@Data
public class ProductPrice {

    private double mrp;
    private double sellingPrice;
    private double price;
    private double priceAfterTax;
    private double taxPercentage;

}
