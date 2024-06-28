package org.example.shopservicelayer.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.shopservicelayer.entity.ProductSpecsValue;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class SpecValueParent {
    private Long id;
    private String value;
//    private Long specNameId;
    private List<ProductSpecsValue> children;
//    private List<SpecValue > children;


    @Override
    public String toString() {
        return "SpecValueParent{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", children=" + children +
                '}';
    }
}
