package org.example.shopservicelayer.someRequest;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SortBy{
     private  String sortingBy;
   private String ascOrDesc;

    @Override
    public String toString() {
        return "SortBy{" +
               "sortingBy='" + sortingBy + '\'' +
               ", ascOrDesc='" + ascOrDesc + '\'' +
               '}';
    }
}
