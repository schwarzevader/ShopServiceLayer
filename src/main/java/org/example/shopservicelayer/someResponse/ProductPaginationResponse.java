package org.example.shopservicelayer.someResponse;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.shopservicelayer.dto.ProductDTO;


import java.util.List;

@Getter
@Setter
@NoArgsConstructor



public class ProductPaginationResponse {

    private int totalPages;
    private long totalElements;
    private List<ProductDTO> products;

    public ProductPaginationResponse(int totalPages, long totalElements, List<ProductDTO> products) {
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.products = products;
    }

    @Override
    public String toString() {
        return "{" +
                "totalPage:" + totalPages +
                ", totalElements:" + totalElements +
                ", products:" + products +
                '}';
    }
}
