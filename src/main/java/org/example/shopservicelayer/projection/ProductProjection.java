package org.example.shopservicelayer.projection;

import java.util.List;


//@Projection(name = "partial", types = { Person.class })
public interface ProductProjection {
     Long getId();
//     Long getId();

    String getName();

     String getDescription();
     double getPrice();
    double getRating();
    double getShadowRating();

    List<SpecsItemsDTOInterface> getSpecItemList();

    interface SpecsItemsDTOInterface{
        long getId();
//        long getSpecsItemsId();
        long getQuantity();
    }
}
