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
//@SqlResultSetMapping(
//        name = "SpecValueParentMapping",
//        classes = @ConstructorResult(
//                targetClass = SpecValueParent.class,
//                columns = {
//                        @ColumnResult(name = "id", type = Long.class),
//                        @ColumnResult(name = "value", type = String.class),
//                        @ColumnResult(name = "children", type = List.class),
//                        @ColumnResult(name = "parentId", type = Long.class)
//                }
//        )
//)
//@Table(schema = "public" ,name = "product_spec_value")
public class ProductSpecsValue implements Serializable {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "product_spec_value_id")
    private Long valueId;

//    public ProductSpecsValue(List<ProductSpecsValue> children, long l, String s, Long aLong) {
//        this.children = children;
//    }


//    @Id
//    @ManyToOne
//    private Person person;

    //    private String name;

    @Column(name = "value")
    private String value;





//    @OneToMany(	mappedBy = "productSpecs",
//            fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL,
//            orphanRemoval = true)
//    private List<ProductSpecItem> productSpecItemValue = new ArrayList<>();

    @OneToMany(	mappedBy = "productSpecsValue",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ProductSpecItem> productSpecItemList = new ArrayList<>();




//    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_spec_name_id")
//    @JoinColumn(name = "spec_name",
//            referencedColumnName = "spec_name")
    private ProductSpecName productSpecName;





//////////////////////////////////////////// OneToMany alternative

    @OneToMany(	mappedBy = "parent",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    public List<ProductSpecValueRelation> parents = new ArrayList<>();

    @OneToMany(	mappedBy = "children",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    public List<ProductSpecValueRelation> childrens = new ArrayList<>();

    ///////////////////////////////////////////////////////////

    ///////////////// parent children
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "spec_value_parent_id")
//    private ProductSpecsValue specValueParent;
//    @OneToMany(
//            mappedBy = "specValueParent",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    private List<ProductSpecsValue> children = new ArrayList<>();
//    public void addChildren(ProductSpecsValue children){
//        this.children.add(children);
//    }
//    public void removeChildren(ProductSpecsValue children){
//        this.children.remove(children);
//    }
//    public void removeParent(){
//        this.specValueParent=null;
//    }

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
///////////////////////////////////////////////////




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
