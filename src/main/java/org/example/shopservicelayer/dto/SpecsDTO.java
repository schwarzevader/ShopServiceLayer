package org.example.shopservicelayer.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@RequiredArgsConstructor
public class SpecsDTO {

    private String specName;

    private List<String> specsItem;
}
