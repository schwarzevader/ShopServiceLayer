package org.example.shopservicelayer.someResponse;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.shopservicelayer.dto.CategoriesAndSpecsDto;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoriesResponse implements Serializable {
    private List<CategoriesAndSpecsDto> data;

    @Override
    public String toString() {
        return "{" +
                "data:" + data.toString() +
                '}';
    }
}
