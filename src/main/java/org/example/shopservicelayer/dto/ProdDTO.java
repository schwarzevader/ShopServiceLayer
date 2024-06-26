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
public class ProdDTO implements Serializable {

    private Long id;
    private String name;
    private String description;
    private double price;
    private double rating;
    private double shadowRating;
    private List<SpecsItemsDTO> specItemList= new ArrayList<>();

    public ProdDTO(Long id, String name, String description, double price, double rating, double shadowRating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.shadowRating = shadowRating;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdDTO prodDTO = (ProdDTO) o;
        return Objects.equals(id, prodDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ProdDTO{" +
               "productId=" + id +
               ", name='" + name + '\'' +
               ", description='" + description + '\'' +
               ", price=" + price +
               ", rating=" + rating +
               ", shadowRating=" + shadowRating +
               ", specItemList=" + specItemList.toString() +
               '}';
    }
}
