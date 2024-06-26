package org.example.shopservicelayer.projection.rec;

import java.util.List;
import java.util.Objects;

public record SpecsP(Long id, String name, List<SpecValuePl> productSpecValues ) {
//    Long getId();
//    String getName();
//    Collection<SpecValueP> getProductSpecValues();
//
//   public record SpecValueP (String value, Long valueId) {
////
////        Long valueId();
////        String value();
////
////
//
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        SpecValueP that = (SpecValueP) o;
//        return Objects.equals(valueId, that.valueId) && Objects.equals(value, that.value);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(valueId, value);
//    }
//}
    public record SpecValuePl(Long valueId,String value){

    Long getValueId() {
        return null;
    }

    String getValue() {
        return null;
    }

}
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecsP specsP = (SpecsP) o;
        return Objects.equals(id, specsP.id) && Objects.equals(name, specsP.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
