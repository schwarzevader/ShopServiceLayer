package org.example.shopservicelayer.projection;

import java.util.Collection;

//public interface SpecsProjection {
//
//    Long getId();
//
//    String getName();
//
//
//    Collection<SpecValueProjection> getProductSpecValues();
//
//    interface SpecValueProjection {
//
//
//        String getProductSpecValues();
//
//
////        String getSpecName();
//
//        Long getSpecId();
//
//
//    }
////    List<SpecValue> getValues();
//}


public interface SpecsProjection {
    Long getId();
    String getName();
    Collection<SpecValueProjection> getProductSpecValues();

    interface SpecValueProjection {

        Long getValueId();
        String getValue();


    }
}