package org.example.shopservicelayer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.shopservicelayer.entity.Product;
import org.example.shopservicelayer.entity.ProductSpecsValue;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Getter

public class ProductUpdateDto implements Serializable {
    private Long id;
    private Product product;
    private List<ProductSpecsValue> removeSpecValue;
    private List<ProductSpecsValue> addSpecValue;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductUpdateDto that = (ProductUpdateDto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", product:" + product +
                ", removeSpecValue:" + removeSpecValue.toString() +
                ", addSpecValue:" + addSpecValue.toString() +
                '}';
    }
}
