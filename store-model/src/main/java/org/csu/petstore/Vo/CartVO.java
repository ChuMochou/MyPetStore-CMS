package org.csu.petstore.Vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartVO {
    private String itemId;
    private String productId;
    private String attribute1;
    private String attribute2;
    private String attribute3;
    private String attribute4;
    private String attribute5;
    private String productName;
    private int inStock;
    private int quantity;
    private BigDecimal listPrice;
}
