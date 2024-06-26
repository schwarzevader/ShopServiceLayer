package org.example.shopservicelayer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class SpecValueName {
    private  String name;
    private  boolean showAll;

    public SpecValueName(String name) {
        this.name = name;
        this.showAll= false;
    }

    @Override
    public String toString() {
        return "SpecValueName{" +
               "name='" + name + '\'' +
               ", showAll=" + showAll +
               '}';
    }
}
