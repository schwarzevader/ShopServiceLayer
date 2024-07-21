package org.example.shopservicelayer.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serializable;
import java.util.*;


@Entity(name = "productSpecValue")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_spec_value")
@NamedEntityGraph(
        name = "ProductSpecsValue")
public class ProductSpecsValue implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "product_spec_value_id")
    private Long valueId;


    @Column(name = "value")
    private String value;



//    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_spec_name_id")
//    @JoinColumn(name = "spec_name",
//            referencedColumnName = "spec_name")
    private ProductSpecName productSpecName;


    @ManyToMany(mappedBy = "childrens", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @Fetch(FetchMode.SUBSELECT)
    private Set<ProductSpecsValue> parents = new HashSet<>();

    @JoinTable(name = "product_spec_value_relation",
            joinColumns = {@JoinColumn(name = "product_spec_value_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_spec_value_relation_id")})
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @Fetch(FetchMode.SUBSELECT)
    private Set<ProductSpecsValue> childrens = new HashSet<>();

    public void addParent(ProductSpecsValue parent) {
//        if (!this.parents.isEmpty()) {
//            throw new IllegalArgumentException();
//        }
        this.parents.add(parent);
        parent.getChildrens().add(this);

    }




    ////////////////////////////////////////
//    @OneToMany(	mappedBy = "parent",
//            fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL,
//            orphanRemoval = true)
//    public List<ProductSpecsValue> parents = new ArrayList<>();

//    @OneToMany(	mappedBy = "childrens",
//            fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL,
//            orphanRemoval = true)
//    public List<ProductSpecsValue> childrens = new ArrayList<>();

//    public ProductSpecsValue addProductSpecItem(ProductSpecItem productSpecItem) {
//        productSpecItemList.add(productSpecItem);
//        productSpecItem.setProductSpecsValue(this);
//        return this;
//    }
//
//
//    public ProductSpecsValue removeProductSpecItem(ProductSpecItem productSpecItem) {
//        productSpecItemList.remove(productSpecItem);
//        productSpecItem.setProductSpecsValue(null);
//        return this;
//    }
    ///////////////////////////////////////////////////////


    @Override
    public String toString() {
        return "ProductCharacteristic{" +
                '\'' +
                ", value='" + value + '\'' +
                '}';
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProductSpecsValue product = (ProductSpecsValue) o;
        return Objects.equals(valueId, product.valueId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valueId);
    }




}
