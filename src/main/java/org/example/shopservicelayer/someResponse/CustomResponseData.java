package org.example.shopservicelayer.someResponse;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomResponseData {
//    private List<SpecKey> data;
    private Map<?,?> data;


    @Override
    public String toString() {
        return "{" +
               "data:" + data +
               '}';
    }
}
