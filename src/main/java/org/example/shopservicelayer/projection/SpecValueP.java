package org.example.shopservicelayer.projection;

import java.util.Objects;

public record SpecValueP (Long valueId ,String value) {
//
//        Long valueId();
//        String value();
//
//


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecValueP that = (SpecValueP) o;
        return Objects.equals(value, that.value) && Objects.equals(valueId, that.valueId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, valueId);
    }
}