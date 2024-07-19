package org.example.shopservicelayer.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Entity(name = "productSpecItem")
@Getter
@Setter
@NoArgsConstructor
//@Table(schema = "public" ,name = "product_spec_items")
@Table(name = "product_spec_items")
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        scope = ProductSpecItem.class,
//        resolver =EntityIdResolver.class
//)
public class ProductSpecItem implements Serializable {
    public ProductSpecItem(Product product, ProductSpecsValue productSpecsValue) {
        this.product = product;
        this.productSpecsValue = productSpecsValue;
    }

    //    @EmbeddedId
//    private ProductSpecValueId productSpecValueId;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_spec_item_id")
    private Long id;

//    private String value;


    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId(value = "product_id")
//    @MapsId
    @JoinColumn(name = "product_id")
    private Product product;

    private long sizeQuantity=0;



    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId(value = "product_spec_value_id")
//    @MapsId
    @JoinColumn(name = "product_spec_value_id")
    private ProductSpecsValue productSpecsValue;





    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductSpecItem that = (ProductSpecItem) o;
        return Objects.equals(product, that.product) && Objects.equals(productSpecsValue, that.productSpecsValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, productSpecsValue);
    }

    @Override
    public String toString() {
        return "{" +
               "id=" + id +
               ", product=" + product +
               ", sizeQuantity=" + sizeQuantity +
               ", productSpecsValue=" + productSpecsValue +
               '}';
    }
}
