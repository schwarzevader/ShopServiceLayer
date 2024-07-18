package org.example.shopservicelayer.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Specs implements Serializable {
//    public static final String ID_ALIAS = "product_spec_name_id";
//    public static final String SPEC_ALIAS = "spec_name";

    private Long id;

    private String name;
//    private SpecValueName specValueName;
    private List<SpecValue> productSpecValues = new ArrayList<>();

//    public ProductSpecsNamesDTO (
//            Object[] tuples,
//            Map<String, Integer> aliasToIndexMap) {
//
//        this.id = longValue(tuples[aliasToIndexMap.get(ID_ALIAS)]);
//        this.name = tuples[aliasToIndexMap.get(SPEC_ALIAS)].toString();
////        this.name = stringValue(tuples[aliasToIndexMap.get(SPEC_ALIAS)]);
//    }


    public Specs(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    @Override
    public String toString() {
        return "{" +
               "id:" + id +
               ", name:'" + name + '\'' +
               ", values:" + productSpecValues.toString() +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Specs that = (Specs) o;
        return Objects.equals(id, that.id) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
