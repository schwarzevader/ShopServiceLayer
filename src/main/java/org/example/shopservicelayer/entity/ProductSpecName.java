package org.example.shopservicelayer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "product_spec_name")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
public class ProductSpecName implements Serializable {
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
    private List<ProductSpecsValue> productSpecValues =new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategory;


//    @OneToMany(	mappedBy = "productSpecName",
//            fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL,
//            orphanRemoval = true)
//    private List<ProductSpecNameItem> productSpecNameItems = new ArrayList<>();



    

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
}
