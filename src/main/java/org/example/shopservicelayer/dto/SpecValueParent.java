package org.example.shopservicelayer.dto;

import lombok.*;
import org.example.shopservicelayer.entity.ProductSpecsValue;

import java.util.List;

//@NoArgsConstructor
@Getter
@Setter

@RequiredArgsConstructor
public class SpecValueParent {
    private Long id;
    private String value;
//    private Long specNameId;
    private List<ProductSpecsValue> children;
//    List<SpecValueParent> childrens;
    private Long parentId;

//    public SpecValueParent(Long valueId, List<SpecValueParent> children) {
//        this.id = valueId;
//        this.childrens = children;
//    }


    public SpecValueParent(Long id, String value, Long parentId) {
        this.id = id;
        this.value = value;
        this.parentId = parentId;
    }

    //
    public SpecValueParent(Long id, String value, List<ProductSpecsValue> children, Long parentId) {
        this.id = id;
        this.value = value;
        this.children = children;
        this.parentId = parentId;
    }



    public SpecValueParent(Long id, String value, List<ProductSpecsValue> children) {
        this.id = id;
        this.value = value;
        this.children = children;

    }


//    private List<SpecValue > children;


//    public SpecValueParent(Long id, String value, List<ProductSpecsValue> children) {
//        this.id = id;
//        this.value = value;
//        this.children = children;
//    }

    @Override
    public String toString() {
        return "SpecValueParent{" +
                "id=" + id +
                ", value='" + value + '\'' +
//                ", children=" + children.toString()+
                '}';
    }
}





