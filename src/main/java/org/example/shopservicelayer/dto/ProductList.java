package org.example.shopservicelayer.dto;

import java.util.List;

public class ProductList {

    private List<ProductDTO> productDTOList;

    public ProductList() {

    }

    public ProductList(List<ProductDTO> productDTOList) {
        this.productDTOList = productDTOList;
    }

    public List<ProductDTO> getProductDTOList() {
        return productDTOList;
    }

    public void setProductDTOList(List<ProductDTO> productDTOList) {
        this.productDTOList = productDTOList;
    }
}
