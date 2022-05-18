package com.sparta.northwindweb.entities;

import java.time.LocalDateTime;

public class ProductItem {
    private Product product;
    private LocalDateTime createDate;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }


}
