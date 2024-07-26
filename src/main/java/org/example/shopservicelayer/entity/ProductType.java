package org.example.shopservicelayer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.shopservicelayer.util.EntityVisitor;
import org.example.shopservicelayer.util.Identifiable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table( name = "product_type")
//@Table(schema = "public", name = "product_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductType implements Serializable, Identifiable {


    public static EntityVisitor<ProductType, Identifiable> ENTITY_VISITOR = new EntityVisitor<ProductType, Identifiable>(ProductType.class) {
    };
    @Id
    @Column(name = "product_type_id",unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private long sortValue;

    private String value;
    @OneToMany(	mappedBy = "productType",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ProductCategory> productCategoryList = new ArrayList<>();

    public void addProductCategory(ProductCategory productCategory){
        this.getProductCategoryList().add(productCategory);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductType that = (ProductType) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProductType{" +
                "id=" + id +
                ", sortValue=" + sortValue +
                ", value='" + value + '\'' +
                ", productCategoryList=" + productCategoryList.toString() +
                '}';
    }
}
