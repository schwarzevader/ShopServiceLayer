package org.example.shopservicelayer.dtoTransformer;



import org.example.shopservicelayer.dto.ProductDTOResultSet;
import org.hibernate.transform.ResultTransformer;

import java.util.*;
import java.util.stream.Collectors;

public class ProductDTOTransformer implements ResultTransformer {

    //    private Long id;
//
//    private String name;
//
//    private String describe ;
//
//    private double price;
//    private String category;

    private static final String PRODUCT_ID = "product_id";
    private static final String PRODUCT_NAME = "product_name";
    private static final String COMPANY_ID = "company_id";
    private static final String COMPANY_NAME = "company_name";

    private final Map<Long, ProductDTOResultSet> productDtoMap = new LinkedHashMap<>();

    @Override
    public Object transformTuple(Object[] objects, String[] aliases) {
        List<String> aliasList = Arrays.asList(aliases);
        Map<String, Object> tupleMap = aliasList.stream()
                .collect(Collectors.toMap(a -> a,
                        a -> objects[aliasList.indexOf(a)]));

        ProductDTOResultSet productDTOResultSet = productDtoMap.computeIfAbsent((Long)tupleMap.get(PRODUCT_ID),
                id -> new ProductDTOResultSet((Long)tupleMap.get(PRODUCT_ID),
                        (String)tupleMap.get(PRODUCT_NAME)));

//        productDTOResultSet.getCompanyDTO().add(new CompanyDTO((Long)tupleMap.get(COMPANY_ID),
//                (String) tupleMap.get(COMPANY_NAME)));

        return productDTOResultSet;
    }

    @Override
    public List<ProductDTOResultSet> transformList(List list) {
        return new ArrayList<>(productDtoMap.values());
    }




}
