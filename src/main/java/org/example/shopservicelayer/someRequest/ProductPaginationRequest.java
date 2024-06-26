package org.example.shopservicelayer.someRequest;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductPaginationRequest {

    private int selectedPage;
    private String sortBy;
    private int pageSize;
    private List<Long> specsValueId;
//    private long categoryID;

    @Override
    public String toString() {
        return "ProductPaginationRequest{" +
               "selectedPage=" + selectedPage +
               ", sortBy=" + sortBy +
               ", pageSize=" + pageSize +
               ", specsValueId=" + specsValueId +
//               ", categoryID=" + categoryID +
               '}';
    }
}
