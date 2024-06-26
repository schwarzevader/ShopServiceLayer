package org.example.shopservicelayer.projection;

import java.io.Serializable;

public class SpecV implements Serializable {

    private boolean check;
    private String specVal;
    private Long i;



    public SpecV(String specValue, Long id) {
        this.check = false;
        this.specVal = specValue;
        this.i = id;
    }






    @Override
    public String toString() {
        return "ProductSpecValueDTO{" +
               "active=" + check +
               ", value='" + specVal + '\'' +
               ", id=" + i +
               '}';
    }
}
