package org.example.shopservicelayer.entity;

//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Entity(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

//@RequiredArgsConstructor
@Table(name = "products")
//@Table(name = "products",schema = "public")
//@Builder
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        scope = Product.class,
//        resolver =EntityIdResolver.class
//)

//@DynamicUpdate
public class Product  implements Serializable {
//search by name or category or type
//исп фабрику для продуктов с разными характеристиками



    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_gen")
//    @SequenceGenerator(name = "product_gen", sequenceName = "product_sequence", allocationSize = 50)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id" , unique = true, nullable = false)
    private Long id;;


    private double rating;
    @Column(name = "shadow_rating")
    private double shadowRating;

    private String name ;

    private double price;
    @Column(length = 500)
    private String description;
    //private String descriptionForDTO;
    //    private String reviews ;
    private int quantity = 0;



    @OneToMany(	mappedBy = "product",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIdentityReference(alwaysAsId = true)
    private List<ProductSpecItem> productSpecItemList = new ArrayList<>();



    @ManyToOne(fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    @JoinColumn(name = "product_category_id")
    // @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
//    @JsonIdentityReference(alwaysAsId = true)
    private ProductCategory productCategory;



    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
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
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }





    public void addProductSpecsValue(ProductSpecsValue productSpecsValue) {
        ProductSpecItem productSpecItem = new ProductSpecItem(this,productSpecsValue);
        productSpecItemList.add(productSpecItem);
        productSpecsValue.getProductSpecItemList().add(productSpecItem);
    }

//    DELETE FROMProductSpecItem  WHERE product_id = 1 AND productSpecsValue_id = 3
    public void removeSpecValue(ProductSpecsValue value) {
        for (Iterator<ProductSpecItem> iterator = productSpecItemList.iterator(); iterator.hasNext(); ) {
            ProductSpecItem prodSpecItem = iterator.next();
            if (prodSpecItem.getProduct().equals(this) && prodSpecItem.getProductSpecsValue().equals(value)) {
                iterator.remove();
                prodSpecItem.getProductSpecsValue().getProductSpecItemList().remove(prodSpecItem);
                prodSpecItem.setProduct(null);
                prodSpecItem.setProductSpecsValue(null);
                break;
            }
        }
    }


    public void removeSpecValueS(ProductSpecsValue value) {
        productSpecItemList.stream()
                .filter(prodSpecItem -> prodSpecItem.getProduct().equals(this) && prodSpecItem.getProductSpecsValue().equals(value))
                .findFirst()
                .ifPresent(prodSpecItem -> {
                    productSpecItemList.remove(prodSpecItem);
                    prodSpecItem.getProductSpecsValue().getProductSpecItemList().remove(prodSpecItem);
                    prodSpecItem.setProduct(null);
                    prodSpecItem.setProductSpecsValue(null);
                });
    }

//    public Product addProductSpecItem(ProductSpecItem productSpecItem){
//        productSpecItemList.add(productSpecItem);
//        productSpecItem.setProduct(this);
//        return this;
//    }
//    public Product removeProductSpecItem(ProductSpecItem productSpecItem){
//        productSpecItem.getProductSpecsValue().getProductSpecItemList().remove(productSpecItem);
//        productSpecItem.getProductSpecsValue().removeProductSpecItem(productSpecItem);
//        productSpecItemList.remove(productSpecItem);
//        productSpecItem.setProduct(null);
//        return this;
//    }

}