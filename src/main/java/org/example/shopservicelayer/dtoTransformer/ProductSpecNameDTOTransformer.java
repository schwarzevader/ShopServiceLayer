package org.example.shopservicelayer.dtoTransformer;

public class ProductSpecNameDTOTransformer {
//        implements ResultTransformer {
//
//
//    public static final String SPEC_NAME_ID_ALIAS = "product_spec_name_id";
//    public static final String SPEC_NAME_ALIAS = "spec_name";
//    public static final String SPEC_VALUE_ID_ALIAS = "product_spec_value_id";
//    public static final String SPEC_VALUE_ALIAS = "spec_value";
//
//
//
//
//
//
//    private final Map<Long, ProductSpecsNamesDTO> specsNamesDTOMap = new LinkedHashMap<>();
//
//    @Override
//    public Object transformTuple(Object[] objects, String[] aliases) {
//        List<String> aliasList = List.of(aliases);
//        Map<String, Object> tupleMap = aliasList.stream()
//                .collect(Collectors.toMap(a -> a,
//                        a -> objects[aliasList.indexOf(a)]));
//
//        Long specId = longValue(tupleMap.get(SPEC_NAME_ID_ALIAS));
//        ProductSpecsNamesDTO specsNamesDTO = specsNamesDTOMap.computeIfAbsent(specId,
//                id -> new ProductSpecsNamesDTO(specId, tupleMap.get(SPEC_NAME_ALIAS).toString()));
//
//        Long valueId = longValue(tupleMap.get(SPEC_VALUE_ID_ALIAS));
//        String value = tupleMap.get(SPEC_VALUE_ALIAS).toString();
////        boolean active = booleanValue(tupleMap.get(ACTIVE_ALIAS));
//
//        specsNamesDTO.getValues().add(new ProductSpecValueDTO( value, valueId));
//
//        return specsNamesDTO;
//    }
//
//    @Override
//    public List<ProductSpecsNamesDTO> transformList(List list) {
//        return new ArrayList<>(specsNamesDTOMap.values());
//    }
//
//    private Long longValue(Object obj) {
//        return obj instanceof Number ? ((Number) obj).longValue() : null;
//    }
//
//    private boolean booleanValue(Object obj) {
//        return obj instanceof Boolean ? (Boolean) obj : false;
//    }




////////////////////////////////////////
//    private Map<Long, ProductSpecsNamesDTO> postDTOMap = new LinkedHashMap<>();
//
//    @Override
//    public ProductSpecsNamesDTO transformTuple(Object[] tuple, String[] aliases) {
//        Map<String, Integer> aliasToIndexMap = aliasToIndexMap(aliases);
//
//        Long postId = longValue(
//                tuple[aliasToIndexMap.get(ProductSpecsNamesDTO.ID_ALIAS)]
//        );
//
//        ProductSpecsNamesDTO prodSpecNameDTO = postDTOMap.computeIfAbsent(
//                postId,
//                id -> new ProductSpecsNamesDTO(tuple, aliasToIndexMap)
//        );
//        prodSpecNameDTO.value().add(
//                new ProductSpecValueDTO(tuple, aliasToIndexMap)
//        );
//
//        return prodSpecNameDTO;
//    }
//
//    @Override
//    public List<ProductSpecsNamesDTO> transformList(List collection) {
//        return new ArrayList<>(postDTOMap.values());
//    }
//
//    private Map<String, Integer> aliasToIndexMap(String[] aliases) {
//        Map<String, Integer> aliasToIndexMap = new LinkedHashMap<>();
//
//        for (int i = 0; i < aliases.length; i++) {
//            aliasToIndexMap.put(
//                    aliases[i].toLowerCase(Locale.ROOT),
//                    i
//            );
//        }
//        return aliasToIndexMap;
//    }
    ///////////////////////////////////////

//    private Map<Long, ProductSpecsNamesDTO> productSpecsNamesDTOMap = new LinkedHashMap<>();
//
//    @Override
//    public Object transformTuple(
//            Object[] tuple,
//            String[] aliases) {
//
//        Map<String, Integer> aliasToIndexMap = aliasToIndexMap(aliases);
//
//        Long specNameId = longValue(tuple[aliasToIndexMap.get(ProductSpecsNamesDTO.ID_ALIAS)]);
//
//        ProductSpecsNamesDTO prodSpecNameDTO = productSpecsNamesDTOMap.computeIfAbsent(
//                specNameId,
//                id -> new ProductSpecsNamesDTO(tuple, aliasToIndexMap)
//        );
//
//        prodSpecNameDTO.value().add(
//                new ProductSpecValueDTO(tuple, aliasToIndexMap)
//        );
//
//        return prodSpecNameDTO;
//    }
//
//    @Override
//    public List transformList(List collection) {
//        return new ArrayList<>( productSpecsNamesDTOMap.values());
//    }


//    private Map<String, Integer> aliasToIndexMap(String[] aliases) {
//        if (aliases == null) {
//            throw new IllegalArgumentException("Aliases array cannot be null.");
//        }
//
//        return IntStream.range(0, aliases.length)
//                .boxed()
//                .collect(Collectors.toMap(i -> aliases[i], i -> i, (a, b) -> a, LinkedHashMap::new));
//    }

//    private Map<String, Integer> aliasToIndexMap(String[] aliases) {
//        return Arrays.stream(aliases)
//                .collect(Collectors.toMap(alias -> alias, Arrays.asList(aliases)::indexOf, (a, b) -> a, LinkedHashMap::new));
//    }
/////////////////////////////////////
//    public  Map<String, Integer> aliasToIndexMap(
//            String[] aliases) {
//
//        Map<String, Integer> aliasToIndexMap = new LinkedHashMap<>();
//
//        for (int i = 0; i < aliases.length; i++) {
//            aliasToIndexMap.put(aliases[i], i);
//        }
//
//        return aliasToIndexMap;
//    }
//////////////////////////////////////////////////////
//    @Override
//    public List<ProductSpecsNamesDTO> transformList(List collection) {
//        return collection;
//    }
//
//    @Override
//    public List<ProductSpecsNamesDTO> transformTuple(Object[] tuple, String[] aliases) {
//        Map<String, ProductSpecsNamesDTO> dtoMap = new HashMap<>();
//
//        String name = (String) tuple[0];
//        String value = (String) tuple[1];
//        boolean active = (Boolean) tuple[2];
//
//        ProductSpecValueDTO specValueDTO = new ProductSpecValueDTO();
//        specValueDTO.setActive(active);
//        specValueDTO.setValue(value);
//
//        dtoMap.computeIfAbsent(name, k -> {
//            ProductSpecsNamesDTO specNameDTO = new ProductSpecsNamesDTO();
//            specNameDTO.setName(name);
//            specNameDTO.setValue(new ArrayList<>());
//            return specNameDTO;
//        }).value().add(specValueDTO);
//
//        return new ArrayList<>(dtoMap.values());
//    }


//    @Override
//    public Object transformTuple(Object[] tuple, String[] aliases) {
//        Map<String, ProductSpecsNamesDTO> dtoMap = new HashMap<>();
//
//        String name = (String) tuple[0];
//        String value = (String) tuple[1];
//        boolean active = (Boolean) tuple[2];
//
//        ProductSpecValueDTO specValueDTO = new ProductSpecValueDTO();
//        specValueDTO.setActive(active);
//        specValueDTO.setValue(value);
//
//        if (!dtoMap.containsKey(name)) {
//            ProductSpecsNamesDTO specNameDTO = new ProductSpecsNamesDTO();
//            specNameDTO.setName(name);
//            specNameDTO.setValue(new ArrayList<>());
//            dtoMap.put(name, specNameDTO);
//        }
//
//        dtoMap.get(name).value().add(specValueDTO);
//
//        return dtoMap.values();
//    }
//
//    @Override
//    public List transformList(List collection) {
//        return new ArrayList<>(collection);
//    }


}
