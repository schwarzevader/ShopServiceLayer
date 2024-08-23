package org.example.shopservicelayer.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor

public class ProductDTO implements Serializable {

    private Long id;

    private String name;

    private String description;

    private double price;
    private double rating;
    private double shadowRating;



//    private boolean inCart;
//    private boolean inWishList;


    public ProductDTO(Long id, String name, String description, double price, double rating, double shadowRating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.shadowRating = shadowRating;
    }

    public ProductDTO(Long id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Override
    public String toString() {
        return "{" +
               "id:" + id +
               ", name:'" + name + '\'' +
               ", description:'" + description + '\'' +
               ", price:" + price +
               ", rating:" + rating +
               ", shadowRating:" + shadowRating +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductDTO that)) return false;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getName(), that.getName()) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getId(),
                getName()
        );
    }
}
