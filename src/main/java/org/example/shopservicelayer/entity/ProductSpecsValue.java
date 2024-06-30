package org.example.shopservicelayer.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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

    public ProductSpecsValue(List<ProductSpecsValue> children, long l, String s, Long aLong) {
        this.children = children;
    }


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



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spec_value_parent_id")
    private ProductSpecsValue specValueParent;


    @OneToMany(
            mappedBy = "specValueParent",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ProductSpecsValue> children = new ArrayList<>();


    public void addChildren(ProductSpecsValue children){
        this.children.add(children);
    }

    public void removeChildren(ProductSpecsValue children){
        this.children.remove(children);
    }


    public void removeParent(){
        this.specValueParent=null;
    }





    //    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "following")
//    @Fetch(FetchMode.SUBSELECT)
//    private Set<User> followers = new HashSet<>();
//
//    @JoinTable(name = "user_relation",
//            joinColumns = {@JoinColumn(name = "user_id")},
//            inverseJoinColumns = {@JoinColumn(name = "user_relation_id")})
//    @ManyToMany(cascade = CascadeType.ALL)
//    @Fetch(FetchMode.SUBSELECT)
//    private Set<User> following = new HashSet<>();

//    private Set <ProductSpecsValue> productSpecsValuesSet;


/////////////////////////
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "parent_id")
//    private PostComment parent;
//⠀
//    @OneToMany(
//            mappedBy = "parent",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    private List<PostComment> children = new ArrayList<>();




//    List<PostCommentRecord>  postComments = entityManager.createQuery("""
//    WITH postCommentChildHierarchy AS (
//      SELECT pc.children pc
//      FROM PostComment pc
//      WHERE pc.id = :commentId
//⠀
//      UNION ALL
//⠀
//      SELECT pc.children pc
//      FROM PostComment pc
//      JOIN postCommentChildHierarchy pch ON pc = pch.pc
//      ORDER BY pc.id
//    )
//    SELECT new PostCommentRecord(
//        pch.pc.id,
//        pch.pc.createdOn,
//        pch.pc.review,
//        pch.pc.score,
//        pch.pc.parent.id
//    )
//    FROM postCommentChildHierarchy pch
//    """, PostCommentRecord.class)
//            .setParameter("commentId", 1L)
//            .getResultList();

    ////////////////////////////////////////

    @Override
    public String toString() {
        return "ProductCharacteristic{" +
                '\'' +
                ", value='" + value + '\'' +
                '}';
    }




//    @ManyToOne(fetch = FetchType.LAZY)
//    private ProductCategory productCategory;

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



    public ProductSpecsValue addProductSpecItem(ProductSpecItem productSpecItem) {
        productSpecItemList.add(productSpecItem);
        productSpecItem.setProductSpecsValue(this);
        return this;
    }


    public ProductSpecsValue removeProductSpecItem(ProductSpecItem productSpecItem) {
        productSpecItemList.remove(productSpecItem);
        productSpecItem.setProductSpecsValue(null);
        return this;
    }
}
