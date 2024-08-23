package org.example.shopservicelayer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@Getter
public class ProductInfoDto implements Serializable {
    private Long id;
    private String name;
    private String description;
    private double price;
    private double rating;
    private List<Specs> specsList = new ArrayList<>();

    public ProductInfoDto(Long id, String name, String description, double price, double rating, List<Specs> specsList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.specsList = specsList;
    }

    public ProductInfoDto(Long id, String name, String description, double price, double rating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductInfoDto that = (ProductInfoDto) o;
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
                ", description:'" + description + '\'' +
                ", price:" + price +
                ", rating:" + rating +
                ", specsList:" + specsList.toString() +
                '}';
    }
}
