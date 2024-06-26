package org.example.shopservicelayer.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor

public class SpecsItemsDTO implements Serializable {
    private Long id;
    private long quantity;

    public SpecsItemsDTO(Long id, long quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecsItemsDTO that = (SpecsItemsDTO) o;
        return id == that.id && quantity == that.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity);
    }

    @Override
    public String toString() {
        return "SpecsItemsDTO{" +
               "id=" + id +
               ", quantity=" + quantity +
               '}';
    }
}
