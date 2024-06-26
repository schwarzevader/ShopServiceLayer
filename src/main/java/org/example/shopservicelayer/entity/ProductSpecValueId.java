package org.example.shopservicelayer.entity;


import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductSpecValueId implements Serializable {
//    @Column(name = "product_id")
    private Long productId;

//    @Column(name = "product_spec_value_id")
//    private Long specValueId;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        ProductSpecValueId that = (ProductSpecValueId) o;
//        return Objects.equals(productId, that.productId) && Objects.equals(specValueId, that.specValueId);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(productId, specValueId);
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductSpecValueId that = (ProductSpecValueId) o;
        return Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }
}
