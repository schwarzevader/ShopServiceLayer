package org.example.shopservicelayer.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.shopservicelayer.util.EntityVisitor;
import org.example.shopservicelayer.util.Identifiable;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "product_spec_name")
@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
@Table(name = "product_spec_names")
@NamedEntityGraph(
        name = "ProductSpecName.productSpecValues",
        attributeNodes = @NamedAttributeNode("productSpecValues")
,subgraphs = {
        @NamedSubgraph(name="subjectListGraph",
                attributeNodes = {
                        @NamedAttributeNode(value="productSpecValues")
                }
        )
}
)
//@Table(schema = "public" ,name = "product_spec_names")
//@NaturalIdCache

@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProductSpecName implements Serializable, Identifiable {

    public static EntityVisitor<ProductSpecName, ProductCategory> ENTITY_VISITOR = new EntityVisitor<ProductSpecName, ProductCategory>(ProductSpecName.class) {
        @Override
        public ProductCategory getParent(ProductSpecName visitingObject) {
            return visitingObject.getProductCategory();
        }

        @Override
        public List<ProductSpecName> getChildren(ProductCategory parent) {
            return parent.getProductSpecNames();
        }

        @Override
        public void setChildren(ProductCategory parent) {
            parent.setProductSpecNames(new ArrayList<ProductSpecName>());
        }
    };


    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "product_spec_name_id")
    private Long id;

    @NaturalId
    @Column(name = "spec_name" ,unique = true)
    private String name;

    private long sortValue;


    @OneToMany(	mappedBy = "productSpecName",
//            fetch = FetchType.EAGER,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<ProductSpecsValue> productSpecValues =new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategory;


//    @OneToMany(	mappedBy = "productSpecName",
//            fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL,
//            orphanRemoval = true)
//    private List<ProductSpecNameItem> productSpecNameItems = new ArrayList<>();


//    public ProductSpecName() {
//    }
//
//    public ProductSpecName(Long id, String name, long sortValue, List<ProductSpecsValue> productSpecValues, ProductCategory productCategory) {
//        this.id = id;
//        this.name = name;
//        this.sortValue = sortValue;
//        this.productSpecValues = productSpecValues;
//        this.productCategory = productCategory;
//    }

//    public ProductSpecName(Object o, Object o1, Object o2, Object o3, Object o4) {
//        this.id = (Long) o;
//        this.name = (String) o1;
//        this.sortValue = (long)o2;
//        this.productSpecValues = (List<ProductSpecsValue>) o3;
//        this.productCategory = (ProductCategory) o4;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductSpecName that = (ProductSpecName) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "ProductSpecName{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sortValue=" + sortValue +
                ", productSpecValues=" + productSpecValues.toString() +
                ", productCategory=" + productCategory.toString() +
                '}';
    }
}
