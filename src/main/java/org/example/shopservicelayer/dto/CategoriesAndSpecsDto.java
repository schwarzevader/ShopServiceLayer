package org.example.shopservicelayer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;

import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
//@Cacheable
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CategoriesAndSpecsDto implements Serializable {
    private Long id;
    private String name;
    private List<Specs> specsList = new ArrayList<>();
    private Map<Long,Specs> specsMap = new HashMap<>();

    public CategoriesAndSpecsDto(Long id, String name, Specs specs) {
        this.id = id;
        this.name = name;
        specsMap.put(specs.getId(),specs);
//        specsMap.computeIfAbsent(specs.getId(),k->
//            new Specs(specs.getId(),specs.getName())).s;
    }

    public CategoriesAndSpecsDto(Long id, String name, List<Specs> specsList) {
        this.id = id;
        this.name = name;
        this.specsList = specsList;
    }

    public CategoriesAndSpecsDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoriesAndSpecsDto that = (CategoriesAndSpecsDto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "---------------------------{" +
                "id:" + id +
                ", name:'" + name + '\'' +
                ", specsList:" + specsList.toString() +
                '}';
    }
}
