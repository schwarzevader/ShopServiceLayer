package org.example.shopservicelayer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SpecKey implements Serializable {
    private Long specNameId;
    private String specName;
    private  boolean showAll;

//    private List<ValueOfSpec> valueOfSpecList;
//    public SpecKey(Long id, String name ,List<ValueOfSpec> valueOfSpecList) {
//        this.id = id;
//        this.specName = name;
//        this.showAll=false;
//        this.valueOfSpecList= valueOfSpecList;
//    }
//    public void addToList(ValueOfSpec valueOfSpec){
//        this.valueOfSpecList.add(valueOfSpec);
//    }

    public SpecKey(Long id, String specName) {
        this.specNameId = id;
        this.specName = specName;
        this.showAll=false;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecKey specKey = (SpecKey) o;
        return Objects.equals(specNameId, specKey.specNameId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(specNameId);
    }




    @Override
    public String toString() {
        return "{" +
               "\"specNameId\":" + specNameId + "," +
               "\"specName\":\"" + specName + "\"," +
               "\"showAll\":" + showAll +
               "}";
    }

//    @Override
//    public String toString() {
//        return
//               this.specNameId +
//               "," + this.specName ;
//    }
}
