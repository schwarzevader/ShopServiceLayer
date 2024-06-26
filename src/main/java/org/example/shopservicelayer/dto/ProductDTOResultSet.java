package org.example.shopservicelayer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTOResultSet implements Serializable {

    private Long id;

    private String name;

    private String describe ;

    private double price;
    private String company;



    //private CompanyDTO companyDTO;


    public ProductDTOResultSet(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
