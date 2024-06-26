package org.example.shopservicelayer.service.serviceImp;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.example.shopservicelayer.dto.*;
import org.example.shopservicelayer.repositories.imp.ProductSpecNameRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;


@Service
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProductServiceImp {


    @Autowired
    private ProductSpecNameRepositoryImp productSpecNameRepositoryImp;


    public Map<String, List<SpecValue>> getProductSpecsById(Long id) {
        return fromListToMap(productSpecNameRepositoryImp.getProductSpecsNamesDTOListByProductId(id));
    }


    private Map<String, List<SpecValue>> fromListToMap(List<Specs> list) {
        return list.stream()
                .collect(groupingBy(
                        Specs::getName,
                        Collectors.flatMapping(psn -> psn.getProductSpecValues().stream(), Collectors.toList())
                ));
    }

    public Map<String, List<SpecValue>> get(Long id) {

        System.out.println("---------------get------");
        List<Specs> list = productSpecNameRepositoryImp.getProductSpecsNamesDTOList(id);
//        System.out.println("start new list-----------------------");
//
//        list.forEach(specs -> {
//            System.out.print("Specs ID: " + specs.getId());
//            System.out.print(" Specs Name: " + specs.getName());
//            System.out.println();
//            System.out.print(" ------------- SpecsList --------------- : ");
//            System.out.println();
//            specs.getProductSpecValues().stream().forEach(specValue -> {
//                System.out.println("  SpecValue ID: " + specValue.getId());
//                System.out.println("  SpecValue: " + specValue.getSpecValue());
//                System.out.println("  SpecName: " + specValue.getSpecName());
//                System.out.println("  SpecNameId: " + specValue.getSpecNameId());
//                System.out.println("  -----------------: " );
//            });
//        });
//        System.out.println("end new list-----------------------");

        return fromListToMap(list);

    }

    public Map<SpecKey, List<ValueOfSpec>> getMap(Long id) {
        return productSpecNameRepositoryImp.getProductSpecsNamesDTOMap(id);
    }


    private Map<SpecValueName, List<SpecValue>> fromListToMapKV(List<Specs> list) {
        return list.stream()
                .map(specs -> new AbstractMap.SimpleEntry<>(new SpecValueName(specs.getName()), specs.getProductSpecValues())).collect(groupingBy(Map.Entry::getKey, Collectors.flatMapping(entry -> entry.getValue().stream(), Collectors.toList())));
    }

//    private Map<SpecValueName, List<SpecValue>> fromListToMapKVS(List<Specs> list) {
//        return list.stream()
//                .collect(groupingBy(
//                        psn -> new SpecValueName(psn.getName()),
//                        Collectors.flatMapping(psn -> psn.getValues().stream(), Collectors.toList())
//                ));
//    }
private Map<SpecValueName, List<SpecValue>> fromListToMapKVS(List<Specs> list) {
//    return list.stream()
//            .collect(groupingBy(
//                    psn -> {
//                        List<SpecValue> values = psn.getValues();
//                        System.out.println("list -----------------------values");
//                        values.forEach(System.out::println);
//
//                        return new SpecValueName(psn.getName());
//                    },
//                    Collectors.flatMapping(psn -> psn.getValues().stream(), Collectors.toList())
//            ));


//    return list.stream()
//            .flatMap(specs -> specs.getValues().stream())
//            .collect(Collectors.groupingBy(
//                    specValue -> new SpecValueName(specValue.getSpecName()),
//                    Collectors.toList()));


//    Map<Integer, List<Person>> personsByAge = persons
//            .stream()
//            .collect(Collectors.groupingBy(p -> p.age));



//    Map<Integer, String> map = persons
//            .stream()
//            .collect(Collectors.toMap(
//                    p -> p.age,
//                    p -> p.name,
//                    (name1, name2) -> name1 + ";" + name2));


//    Map<Object, List<Integer>> map = persons.stream()
//            .collect(groupingBy(person -> new Pair<>(person.salary(), person.department()),
//                    mapping(Person::id, toList())));




   return list.stream()
            .collect
                    (groupingBy(spec-> new SpecValueName(spec.getClass().getName())
                            ,Collectors.flatMapping(psn -> psn.getProductSpecValues().stream(), Collectors.toList())
                            ));

//    return list.stream()
//            // группируем по имени спецификации
//            // group by the specification name
//            .collect(Collectors.groupingBy(spec -> new SpecValueName(spec.getName())))
//            // преобразуем значения в списки значений спецификации
//            // transform the values into lists of specification values
//            .entrySet().stream()
//            .collect(Collectors.toMap(
//                    Map.Entry::getKey,
//                    entry -> entry.value().stream()
//                            .flatMap(spec -> spec.getValues().stream())
//                            .collect(Collectors.toList())
//            ));

}




    public Map<SpecValueName, List<SpecValue>> getS(Long id) {
        return fromListToMapKVS(productSpecNameRepositoryImp.getProductSpecsNamesDTOList(id));
//        return fromListToMapKV(productSpecNameRepositoryImp.getProductSpecsNamesDTOList(id));
    }

    public List<Specs> getProdSpecNameList(Long id) {
        return toSpecNameList(productSpecNameRepositoryImp.getProductSpecsNamesDTOList(id));
    }

    private List<Specs> toSpecNameList(List<Specs> list) {
        return list.stream()
                .collect(Collectors.groupingBy(Specs::getId))
                .values()
                .stream()
                .map(group -> {
                    Specs first = group.get(0);
                    first.setProductSpecValues(group.stream().flatMap(dto -> dto.getProductSpecValues().stream()).collect(Collectors.toList()));
                    return first;
                })
                .collect(Collectors.toList());
    }
}
