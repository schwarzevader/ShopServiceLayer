package org.example.shopservicelayer.repositories.imp;


import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.example.shopservicelayer.dto.SpecKey;
import org.example.shopservicelayer.dto.SpecValue;
import org.example.shopservicelayer.dto.Specs;
import org.example.shopservicelayer.dto.ValueOfSpec;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ProductSpecNameRepositoryImp {

    @PersistenceContext
    private EntityManager entityManager;
    private int i = 0;
    private int j = 0;

    private int index=0;

    private long currentId ;






//    private final String orderByPriceAsc="order by p.price asc";
//    private final String orderByPriceDesc="order by p.price desc";
//    private final String orderByRating="order by p.rating desc";
//    private final String orderByShadowRating="order by p.shadowRating desc";

//    public List<ProductSpecsNamesDTO> getProductSpecsNamesDTOList(Long categoryId) {
//        return entityManager.createQuery(
//                        "select pSN.id ," +
//                                "pSN.name ," +
//                                "pSV.value ," +
//                                "pSV.id " +
//                                "from product_spec_name pSN " +
//                                "join pSN.productSpecValues pSV " +
//                                "join pSN.productCategory pC " +
//                                "where pC.id =: value")
//                .setParameter("value", categoryId)
//                .unwrap(org.hibernate.query.Query.class)
//                .setTupleTransformer((tuples, aliases) -> {
//                    ProductSpecsNamesDTO prodSpecNameDTO = new ProductSpecsNamesDTO();
//                    prodSpecNameDTO.setId((long) tuples[0]);
//                    prodSpecNameDTO.setName((String) tuples[1]);
//                    prodSpecNameDTO.getValues().add(new ProductSpecValueDTO((String) tuples[2], (Long) tuples[3]));
//                    return prodSpecNameDTO;
//                }).getResultList();
//    }


    public Map<SpecKey, List<ValueOfSpec>> getProductSpecsNamesDTOMap(Long categoryId) {
        i=0;

//        EntityGraph<ProductSpecName> graph = entityManager.createEntityGraph(ProductSpecName.class);
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("ProductSpecName.productSpecValues");

        Map<SpecKey, List<ValueOfSpec>> specsListMap = new HashMap<>();
        entityManager.createQuery(
                        "select  pSN.id ," +
                        "pSN.name ," +
                        "pSV.value ," +
                        "pSV.id " +
                        "from product_spec_name pSN " +
                        "join pSN.productSpecValues pSV " +

                        "where pSN.productCategory.id =: value")
//                        "where pC.id =: value")
                .setParameter("value", categoryId)
//                .setHint("javax.persistence.fetchgraph", entityGraph)
                .unwrap(Query.class)
                .setTupleTransformer((tuples, aliases) -> {
                    SpecKey tempSpec = new SpecKey((long) tuples[0], (String) tuples[1]);
                    if (specsListMap.containsKey(tempSpec)) {
//                        specsListMap.get(tempSpec).add(new SpecValue((String) tuples[2], (Long) tuples[3], (String) tuples[1]));
                        specsListMap.get(tempSpec).add(new ValueOfSpec((Long) tuples[3],(String) tuples[2], (Long) tuples[0]));
                    } else {
                        SpecKey prodSpecNameDTO = new SpecKey((Long) tuples[0], (String) tuples[1]);
//                        SpecValue specValue = new SpecValue((String) tuples[2], (Long) tuples[3], (String) tuples[1]);
//                        SpecValue specValue = new SpecValue((Long) tuples[3],(String) tuples[2], (Long) tuples[0]);
                        ValueOfSpec specValue = new ValueOfSpec((Long) tuples[3],(String) tuples[2], (Long) tuples[0]);
//                        List<SpecValue> list = new ArrayList<>();
                        List<ValueOfSpec> list = new ArrayList<>();
                        list.add(specValue);
                        specsListMap.put(prodSpecNameDTO, list);
                    }
//                    Specs prodSpecNameDTO = new Specs();
//                    prodSpecNameDTO.setId((long) tuples[0]);
//                    prodSpecNameDTO.setName((String) tuples[1]);
//                    prodSpecNameDTO.getProductSpecValues().add(new SpecValue((String) tuples[2], (Long) tuples[3],(String) tuples[1]));

//                    System.out.println("tuples------1--"+tuples[0] );
//                    System.out.println("tuples------2--"+tuples[1] );
//                    System.out.println("tuples------3--"+tuples[2] );
//                    System.out.println("tuples------4--"+tuples[3] );

//                    System.out.println("count-----------------------------" + i);
//                    i++;
                    return null;
                })
                .getResultList();

        return specsListMap;
    }


    public  List<SpecKey> geSpecsNamesAndSpecValuesDTOList(Long categoryId) {

//        EntityGraph<ProductSpecName> graph = entityManager.createEntityGraph(ProductSpecName.class);
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("ProductSpecName.productSpecValues");

        Map<SpecKey, List<ValueOfSpec>> specsListMap = new HashMap<>();
        List<SpecKey> specKeys= new ArrayList<>();
        entityManager.createQuery(
                        "select  " +
                        "pSN.id ," +
                        "pSN.name ," +
                        "pSV.value ," +
                        "pSV.id " +
                        "from product_spec_name pSN " +
                        "join pSN.productSpecValues pSV " +

                        "where pSN.productCategory.id =: value")
//                        "where pC.id =: value")
                .setParameter("value", categoryId)
//                .setHint("javax.persistence.fetchgraph", entityGraph)
                .unwrap(Query.class)
                .setTupleTransformer((tuples, aliases) -> {
                    SpecKey tempSpec = new SpecKey((long) tuples[0], (String) tuples[1]);
                    if (specsListMap.containsKey(tempSpec)) {
//                        specsListMap.get(tempSpec).add(new SpecValue((String) tuples[2], (Long) tuples[3], (String) tuples[1]));
                        specsListMap.get(tempSpec).add(new ValueOfSpec((Long) tuples[3],(String) tuples[2], (Long) tuples[0]));
                    } else {
                        SpecKey prodSpecNameDTO = new SpecKey((Long) tuples[0], (String) tuples[1]);
//                        SpecValue specValue = new SpecValue((String) tuples[2], (Long) tuples[3], (String) tuples[1]);
//                        SpecValue specValue = new SpecValue((Long) tuples[3],(String) tuples[2], (Long) tuples[0]);
                        ValueOfSpec specValue = new ValueOfSpec((Long) tuples[3],(String) tuples[2], (Long) tuples[0]);
//                        List<SpecValue> list = new ArrayList<>();
                        List<ValueOfSpec> list = new ArrayList<>();

                        list.add(specValue);
                        specsListMap.put(prodSpecNameDTO, list);
                    }
                    System.out.println("count-----------------------------" + i);
                    i++;
                    return null;
                })
                .getResultList();
        return specKeys;
    }

    public List<Specs> getProductSpecsNamesDTOList(Long id) {
        System.out.println("+++++++++++++getProductSpecsNamesDTOList++++++++++");
        j=0;
        this.index=0;
        this.currentId=-1;


//        EntityGraph<ProductSpecName> graph = entityManager.createEntityGraph(ProductSpecName.class);

        EntityGraph<?> entityGraph = entityManager.getEntityGraph("ProductSpecName.productSpecValues");

//        return entityManager.createQuery(
//                        "select distinct  pSN.id ," +
//                        "pSN.name ," +
//                        "pSV.value ," +
//                        "pSV.id " +
//                        "from product_spec_name pSN " +
//                        "join pSN.productSpecValues pSV " +
//
//                        "where pSN.productCategory.id =: value")
////                        "where pC.id =: value")
//                .setParameter("value", id)
////                .setHint("javax.persistence.fetchgraph",entityGraph)
//                .unwrap(Query.class)
//                .setTupleTransformer((tuples, aliases) -> {
//                    Specs prodSpecNameDTO = new Specs();
//                    prodSpecNameDTO.setId((long) tuples[0]);
//                    prodSpecNameDTO.setName((String) tuples[1]);
//                    prodSpecNameDTO.getProductSpecValues().add(new SpecValue((String) tuples[2], (Long) tuples[3],(String) tuples[1]));
//                    return prodSpecNameDTO;
//                }).getResultList();


//        left join product_categories pc1_0 on pc1_0.product_category_id=psn1_0.product_category_id
//        left join product_spec_value psv1_0 on psn1_0.product_spec_name_id=psv1_0.product_spec_name_id



//        "select  pSN.id  ," +
//        " pSN.name  ," +
////           "v.productSpecName.id ," +
//        " v.id," +
//        " v.value " +
//        "from product_spec_name pSN " +
//        "left join pSN.productSpecValues v " +
//        "left join pSN.productCategory pC " +
////           "left join pSN.productSpecValues v on pSN.id = v.productSpecName.id " +
////           "left join pSN.productCategory pC on pSN.productCategory.id = pC.id " +
//        "where pC.id = :id"


//        public List<Specs> getProductSpecsNamesDTOList(Long id) {
//            return entityManager.createQuery(
//                            "select new com.example.Specs(pSN.id, pSN.name, " +
//                            "(select new com.example.SpecValue(pSV.id, pSV.value) " +
//                            "from ProductSpecsValue pSV where pSV.productSpecName.id = pSN.id)) " +
//                            "from ProductSpecName pSN " +
//                            "left join pSN.productCategory pC " +
//                            "where pC.id = :value", Specs.class)
//                    .setParameter("value", id)
//                    .getResultList();
//        }


        List<Specs>  specsList = new ArrayList<>();

        Map<Long, Specs> specsMap = new HashMap<>();


//        return entityManager.createQuery(
        entityManager.createQuery(
                        "select  " +
                        "pSN.id" +
                        ", pSN.name" +
                        ", pSV.value" +
                        ", pSV.id " +
                        "from product_spec_name  pSN " +
                        "left join   pSN.productSpecValues pSV on pSN.id = pSV.productSpecName.id "+
                        "left join  pSN.productCategory pC on pSN.productCategory.id=pC.id " +
                        "where pC.id = :value " +
                        "order by pSN.sortValue")

                .setParameter("value", id)
                .unwrap(Query.class)
                .setResultTransformer((tuples, aliases) -> {

//                    Specs prodSpecNameDTO = new Specs();
//                    prodSpecNameDTO.setId((Long) tuples[0]);
//                    prodSpecNameDTO.setName((String) tuples[1]);

                    Long specId= (Long) tuples[0];
                    SpecValue specValue= new SpecValue( (Long) tuples[3],(String) tuples[2],  specId);



//////////////// 1

//                    specsMap.computeIfAbsent(specId, k -> new Specs(specId, (String) tuples[1], new ArrayList<>(List.of(specValue))))
                    specsMap.computeIfAbsent(specId, k -> new Specs(specId, (String) tuples[1]))
//                    specsMap.computeIfAbsent(specId, k -> new Specs(specId, (String) tuples[1], Stream.of(specValue).collect(Collectors.toList())))
                            .getProductSpecValues().add(specValue);

/////////////////1

//////////////////////2
//                    if (this.currentId==prodSpecNameDTO.getId()){
////                    if (specsList.contains(prodSpecNameDTO)){
//                        System.out.println("if contains========"+"index="+index+"---"+prodSpecNameDTO.getName()+" -- id = "+prodSpecNameDTO.getId());
//
//                        specsList.get(index).getProductSpecValues().add(specValue);
//
//                    }else {
//                        prodSpecNameDTO.getProductSpecValues().add(specValue);
//                        if (this.currentId<0){
//                            this.currentId=prodSpecNameDTO.getId();
//                            System.out.println("-----------------first add in index-="+index+" current id = "+this.currentId);
//                            specsList.add(index,prodSpecNameDTO);
//
//                        }else {
//                            index++;
//                            currentId=prodSpecNameDTO.getId();
//                            System.out.println("-----------------other add in index-="+index+" current id = "+this.currentId);
//                            specsList.add(index,prodSpecNameDTO);
//
//                        }
////                        currentId=prodSpecNameDTO.getId();
////                        prodSpecNameDTO.getProductSpecValues().add(new SpecValue( (Long) tuples[3],(String) tuples[2],  (Long) tuples[0]));
////                        System.out.println("index  before ++ ======="+index);
////                        specsList.add(index,prodSpecNameDTO);
//
//                    }
//////////////////////2

//////////////////////3

//                 Specs  prodSpecNameDTO =  new Specs((Long) tuples[0], (String) tuples[1],
//                            Stream.of(new SpecValue( (Long) tuples[3],(String) tuples[2],  (Long) tuples[0])).collect(Collectors.toList()));

///////////////////////3

//                    prodSpecNameDTO.getProductSpecValues().add(new SpecValue((String) tuples[2], (Long) tuples[3], (String) tuples[1]));

//                    prodSpecNameDTO.getProductSpecValues().add(new SpecValue( (Long) tuples[3],(String) tuples[2],  (Long) tuples[0]));
//                    System.out.println(prodSpecNameDTO.toString());

//                    j++;
//                    System.out.println("count i =============="+j);

//                    return prodSpecNameDTO;
                    return null;
                })
                .getResultList()
//                .stream().distinct()
        ;
//        return specsList;
        return specsMap.values().stream().toList();

    }






//    public List<ProductSpecsNamesDTO> getProductSpecsNamesDTOListByProductId(Long productId) {
//        return entityManager.createQuery(
//                        "select pSN.id ," +
//                        "pSN.name ," +
//                        "pSV.value ," +
//                        "pSV.id " +
//                        "from product_spec_name pSN " +
//                        "join pSN.productSpecValues pSV " +
//                        "join pSV.productSpecItemList pSI " +
//                        "join pSI.product p " +
//                        "where p.id =: value")
//                .setParameter("value", productId)
//                .unwrap(org.hibernate.query.Query.class)
//                .setTupleTransformer((tuples, aliases) -> {
//                    ProductSpecsNamesDTO prodSpecNameDTO = new ProductSpecsNamesDTO();
//                    prodSpecNameDTO.setId((long) tuples[0]);
//                    prodSpecNameDTO.setName((String) tuples[1]);
//                    prodSpecNameDTO.getValues().add(new ProductSpecValueDTO((String) tuples[2], (Long) tuples[3]));
//                    return prodSpecNameDTO;
//                }).getResultList();
//    }




    public List<Specs> getProductSpecsNamesDTOListByProductId(Long productId) {
//        return entityManager.createQuery(
//                        "select pSN.id ," +
//                        "pSN.name ," +
//                        "pSV.value ," +
//                        "pSV.id " +
//                        "from product_spec_name pSN " +
//                        "join pSN.productSpecValues pSV " +
//                        "join pSV.productSpecItemList pSI " +
//                        "join pSI.product p " +
//                        "where p.id =: value")
//                .setParameter("value", productId)
//                .unwrap(org.hibernate.query.Query.class)
//                .setTupleTransformer((tuples, aliases) -> {
//                    Specs prodSpecNameDTO = new Specs();
//                    prodSpecNameDTO.setId((long) tuples[0]);
//                    prodSpecNameDTO.setName((String) tuples[1]);
//                    prodSpecNameDTO.getValues().add(new SpecValue((String) tuples[2], (Long) tuples[3],(String) tuples[1]));
//                    return prodSpecNameDTO;
//                }).getResultList();
        return  null;
    }



    public List<Specs> getProductSpecsNamesDTOList(String searchString) {
//        return entityManager.createQuery(
//                        "select pSN.id ," +
//                                "pSN.name ," +
//                                "pSV.value ," +
//                                "pSV.id " +
//                                "from product_spec_name pSN " +
//                                "join pSN.productSpecValues pSV " +
//                                "where pSV.value =: value")
//                .setParameter("value", searchString)
//                .unwrap(org.hibernate.query.Query.class)
//                .setTupleTransformer((tuples, aliases) -> {
//                    Specs prodSpecNameDTO = new Specs();
//                    prodSpecNameDTO.setId((long) tuples[0]);
//                    prodSpecNameDTO.setName((String) tuples[1]);
//                    prodSpecNameDTO.getValues().add(new SpecValue(true,(String) tuples[2], (Long) tuples[3],(String)tuples[1]));
//                    return prodSpecNameDTO;
//                }).getResultList();
        return  null;
    }

//    public List<ProductDTO> getProductDTO(List<Long> valuesList
//            ,  int minPrice , int maxPrice ){
//        int totalPage;
//        int listSize;
//        Query query = entityManager.createQuery
//                ("select  new com.example.productservice.dto.ProductDTO(\n" +
//                "                p.id ,\n" +
//                "                p.name ,\n" +
//                "                p.description,\n" +
//                "                p.price )\n" +
//                "            from  productSpecItem pSI\n" +
//                "            join pSI.product p\n" +
//                "            join pSI.productSpecsValue pSV\n" +
//                "            where pSV.id in:values\n" +
//                "            AND p.price >= :minPrice\n" +
//                "            AND p.price <= :maxPrice\n" +
//                "            order by p.price asc")
//                .setParameter("values",valuesList)
//                .setParameter("minPrice",minPrice)
//                .setParameter("maxPrice",maxPrice);
//        int maxCount = query.getMaxResults();
//
//    }




    public Map<SpecKey, List<SpecValue>> getProductSpecs(List <Long> brandID) {




        Map<SpecKey, List<SpecValue>> specsListMap = new HashMap<>();
        entityManager.createQuery(
                        "select distinct " +
                        "pSN.id ," +
                        "pSN.name ," +
                        "pSV.value ," +
                        "pSV.id " +
                        "from product_spec_name pSN " +
                        "join pSN.productSpecValues pSV " +
                        "join pSV.productSpecItemList psi " +
                        "where psi.product.id in : value")
//                        "where pC.id =: value")
                .setParameter("value", brandID)
//                .setHint("javax.persistence.fetchgraph", entityGraph)
                .unwrap(Query.class)
                .setTupleTransformer((tuples, aliases) -> {
                    SpecKey tempSpec = new SpecKey((long) tuples[0], (String) tuples[1]);
                    if (specsListMap.containsKey(tempSpec)) {
                        specsListMap.get(tempSpec).add(new SpecValue((String) tuples[2], (Long) tuples[3], (String) tuples[1]));
                    } else {
                        SpecKey prodSpecNameDTO = new SpecKey((long) tuples[0], (String) tuples[1]);
//                        SpecValue specValue = new SpecValue((String) tuples[2], (Long) tuples[3], (String) tuples[1]);
                        SpecValue specValue = new SpecValue( (Long) tuples[3],(String) tuples[2],  (Long) tuples[0]);
                        List<SpecValue> list = new ArrayList<>();
                        list.add(specValue);
                        specsListMap.put(prodSpecNameDTO, list);
                    }
                    return null;
                })
                .getResultList();

        return specsListMap;
    }



    public List<Specs> getProductSpecsList(List <Long> brandID) {

       return  entityManager.createQuery(
                        "select distinct pSN.id ," +
                        "pSN.name ," +
                        "pSV.value ," +
                        "pSV.id " +
                        "from product_spec_name pSN " +
                        "join pSN.productSpecValues pSV " +
                        "join pSV.productSpecItemList psi " +
                        "where psi.product.id in : value")
                .setParameter("value", brandID)
                .unwrap(Query.class)
                .setTupleTransformer((tuples, aliases) -> {
                    Specs prodSpecNameDTO = new Specs();
                    prodSpecNameDTO.setId((Long) tuples[0]);
                    prodSpecNameDTO.setName((String) tuples[1]);
                    prodSpecNameDTO.getProductSpecValues().add(new SpecValue((String) tuples[2], (Long) tuples[3], (String) tuples[1]));
                    return prodSpecNameDTO;
                })
                .getResultList();


    }








    }



