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
public class CategoryDTO implements Serializable {
    private Long id;
    private String value;


//    @Override
//    public String toString() {
//        return "CategoryDTO{" +
//               "id=" + id +
//               ", nameOfCategory='" + value + '\'' +
//               '}';
//    }
}
