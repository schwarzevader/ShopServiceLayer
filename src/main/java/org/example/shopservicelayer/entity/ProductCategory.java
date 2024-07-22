package org.example.shopservicelayer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "product_category")
@Table(name = "product_categories")
//@Table(schema = "public", name = "product_categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@AllArgsConstructor
//@RequiredArgsConstructor
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        scope = ProductCategory.class,
//        resolver =EntityIdResolver.class
//)

@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProductCategory implements Serializable {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_category_id")
    private Long id;

//    @NaturalId
    private String nameOfCategory;
    private long sortValue;

    public ProductCategory(String nameOfCategory) {
        this.nameOfCategory = nameOfCategory;
    }



    //
    @OneToMany(	mappedBy = "productCategory",
            fetch = FetchType.LAZY,
            orphanRemoval = true)
//    @JsonIdentityReference(alwaysAsId = true)
    private List<Product> categoryItems  = new ArrayList<>();

//    @OneToMany(	mappedBy = "productCategory",
//            fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL,
//            orphanRemoval = true)
//    private List<ProductSpecNameItem> productSpecNameItems = new ArrayList<>();



    @OneToMany(	mappedBy = "productCategory",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//    @JoinColumn(name = "product_category_id")
    private List<ProductSpecName> productSpecNames = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_type_id")
    private ProductType productType;
}
