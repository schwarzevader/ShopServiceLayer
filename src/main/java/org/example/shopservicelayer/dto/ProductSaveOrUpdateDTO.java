package org.example.shopservicelayer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.shopservicelayer.entity.Product;
import org.example.shopservicelayer.entity.ProductSpecsValue;


import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductSaveOrUpdateDTO {
    private Long id;
    private Product product ;
    private Long productCategoryId;
    private List<ProductSpecsValue> removeSpecValue;
    private List<ProductSpecsValue>addSpecValue;
}
