package org.example.shopservicelayer.someResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.shopservicelayer.dto.ProductDTO;
import org.example.shopservicelayer.dto.SpecValue;

import java.util.List;
import java.util.Map;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductPaginationAndSpecsResponse {

    private int totalPages;
    private long totalElements;
    private List<ProductDTO> products;
    private Map<String, List<SpecValue>> specs;
}
