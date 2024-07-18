package org.example.shopservicelayer.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductAndSpecsDto implements Serializable {
    private Long id;
    private String name ;
    private double shadowRating;
    private double price;
    private String description;
    private int quantity ;
    private Long categoryID;
    private List<Specs> specsList;

//    public ProductAndSpecsDto(Long id, String name, double shadowRating, double price, String description, int quantity, Long categoryID, List<Specs> specsList) {
//        this.id = id;
//        this.name = name;
//        this.shadowRating = shadowRating;
//        this.price = price;
//        this.description = description;
//        this.quantity = quantity;
//        this.categoryID = categoryID;
//        this.specsList = specsList;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductAndSpecsDto that = (ProductAndSpecsDto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", name:'" + name + '\'' +
                ", shadowRating:" + shadowRating +
                ", price:" + price +
                ", description:'" + description + '\'' +
                ", quantity:" + quantity +
                ", categoryID:" + categoryID +
                ", specsList:" + specsList.toString() +
                '}';
    }
}
