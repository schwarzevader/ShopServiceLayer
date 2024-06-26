package org.example.shopservicelayer.someResponse;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.shopservicelayer.dto.SpecValue;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfoDto {

    private Long id;

    private String name;
    private List<SpecValue> values = new ArrayList<>();
}
