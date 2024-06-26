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
public class ValueOfSpec implements Serializable {

    private Long id;
    private String specValue;
    private Long specNameId;
    private boolean checked;


    public ValueOfSpec(Long id, String specValue, Long specNameId) {
        this.id = id;
        this.specValue = specValue;
        this.specNameId = specNameId;
        this.checked=false;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValueOfSpec that = (ValueOfSpec) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }



    @Override
    public String toString() {
        return "{" +
               "id:" + id +
               ", specValue:'" + specValue + '\'' +
               ", specNameId:" + specNameId +
               ", checked:" + checked +
               '}';
    }
}
