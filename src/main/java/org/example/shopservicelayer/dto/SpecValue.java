package org.example.shopservicelayer.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
public class SpecValue implements Serializable {


    private boolean checked;
    private String specValue;
    private String specName;
    private Long specNameId;
    private Long id;

    public SpecValue(Long id,String specValue, Long specNameId) {
        this.specValue = specValue;
        this.specNameId = specNameId;
        this.id = id;
        this.checked= false;
    }
//    public ProductSpecValueDTO(
//            Object[] tuples,
//            Map<String, Integer> aliasToIndexMap) {
//        this.id = longValue(tuples[aliasToIndexMap.get(ID_ALIAS)]);
//        this.value = tuples[aliasToIndexMap.get(VALUE_ALIAS)].toString();
////        this.review = stringValue(tuples[aliasToIndexMap.get(VALUE_ALIAS)]);
//    }


    public SpecValue(String specValue, Long id) {
        this.checked = false;
        this.specValue = specValue;
        this.specName = specName;
        this.id = id;

    }


    public SpecValue(String specValue, Long id, String specName) {
        this.checked = false;
        this.specValue = specValue;
        this.specName = specName;
        this.id = id;

    }

    public SpecValue(boolean active, String specValue, Long id, String specName) {
        this.checked = active;
        this.specValue = specValue;
        this.specName = specName;
        this.id = id;

    }

    @Override
    public String toString() {
        return "{" +
                "checked:" + checked +
                ", specValue:'" + specValue + '\'' +
                ", specName:'" + specName + '\'' +
                ", specNameId:" + specNameId +
                ", id:" + id +
                '}';
    }


//    @Override
//    public String toString() {
//        return "{" +
//               "active:" + checked +
//               ", value:'" + specValue + '\'' +
//               ", id:" + id +
//               '}';
//    }



///////////////////////////////
////    public static final String ID_ALIAS = "pc_id";
////
////    public static final String REVIEW_ALIAS = "pc_review";
//
////    public static final String ID_ALIAS = "product_spec_value_id";
////
////    public static final String VALUE_ALIAS = "spec_value";
//
////    private boolean checked;
//    private String productSpecValues;
//    private String specName;
//    private Long specId;
//
//    private Long  productSpecNameId;
//
//
//
//
////    public ProductSpecValueDTO(
////            Object[] tuples,
////            Map<String, Integer> aliasToIndexMap) {
////        this.id = longValue(tuples[aliasToIndexMap.get(ID_ALIAS)]);
////        this.value = tuples[aliasToIndexMap.get(VALUE_ALIAS)].toString();
//////        this.review = stringValue(tuples[aliasToIndexMap.get(VALUE_ALIAS)]);
////    }
//
//
//    public SpecValue( Long id , String specValue) {
////        this.checked = false;
//        this.productSpecValues = specValue;
////        this.specName = specName;
//        this.specId = id;
//
//    }
//
//    public SpecValue(String productSpecValues,Long specId ,String specName) {
//        this.productSpecValues = productSpecValues;
//        this.specName = specName;
//        this.specId = specId;
//    }
//
//    public SpecValue(String productSpecValues, Long specId, Long productSpecNameId) {
//        this.productSpecValues = productSpecValues;
//        this.specId = specId;
//        this.productSpecNameId = productSpecNameId;
//    }
//
//
//    //
////    public SpecValue(String specValue, Long id, String specName) {
////        this.checked = false;
////        this.specValue = specValue;
////        this.specName = specName;
////        this.id = id;
////
////    }
////
////    public SpecValue(boolean active, String specValue, Long id, String specName) {
////        this.checked = active;
////        this.specValue = specValue;
////        this.specName = specName;
////        this.id = id;
////
////    }
//
//
//
//    @Override
//    public String toString() {
//        return "ProductSpecValueDTO{" +
////               "active=" + checked +
//               ", value='" + productSpecValues + '\'' +
//               ", id=" + specId +
//               ", specName=" + specName +
//               ", specId=" + productSpecNameId +
//               '}';
//    }
}
