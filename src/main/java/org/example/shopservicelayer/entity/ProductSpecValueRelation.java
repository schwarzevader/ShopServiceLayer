package org.example.shopservicelayer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "productSpecValueReletion")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_spec_value_relation")

public class ProductSpecValueRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "product_spec_value_relation_id")
    private Long productSpecValueRelationId;

    public ProductSpecsValue patent;
    public ProductSpecsValue children;



}
