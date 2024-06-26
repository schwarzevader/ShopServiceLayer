package org.example.shopservicelayer.someResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomResponse implements Serializable {
    private Map<?, ?> data ;

//    private List<Specs> data;

//    public SpecNameValueDto(List<ProductSpecsNamesDTO> s) {
//        this.s = s;
//    }
//
//    public List<ProductSpecsNamesDTO> getS() {
//        return s;
//    }
//
//    public void setS(List<ProductSpecsNamesDTO> s) {
//        this.s = s;
//    }
}
