package org.example.shopservicelayer.repositories.imp;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.shopservicelayer.dto.CategoriesAndSpecsDto;
import org.example.shopservicelayer.dto.SpecValue;
import org.example.shopservicelayer.dto.Specs;
import org.example.shopservicelayer.dtoTransformer.dtoTransormInterfaces.SpecsDtoTransformer;
import org.example.shopservicelayer.repositories.repositoriesInterfaces.CategoriesAndSpecsRepository;
import org.hibernate.jpa.QueryHints;
import org.hibernate.query.Query;

import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

@Repository
@Transactional
//@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class CategoriesAndSpecsRepositoryImp implements CategoriesAndSpecsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    StopWatch stopWatch = new StopWatch();

    private SpecsDtoTransformer specsDtoTransformer;

//    @Autowired
//    private DSLContext ctx;
//
//
//    Table<Record> PRODUCT_CATEGORY = table("product_categories");
//    Table<Record> PRODUCT_SPEC_NAME =table("product_spec_names");
//    Table<Record> PRODUCT_SPEC_VALUE = table("product_spec_value");
//
//    Field<Long> CATEGORY_ID = DSL.field("product_category_id", Long.class);
//    Field<String> CATEGORY_NAME = DSL.field("name_of_category", String.class);
//    Field<Long> SPEC_NAME_ID = DSL.field("product_spec_name_id", Long.class);
//    Field<String> SPEC_NAME = DSL.field("spec_name", String.class);
//    Field<Long> SPEC_VALUE_ID = DSL.field("product_spec_value_id", Long.class);
//    Field<String> SPEC_VALUE = DSL.field("value", String.class);
//    Field<Long> SPEC_NAME_CATEGORY_ID = DSL.field("product_category_id", Long.class);
//    Field<Long> VALUE_SPEC_NAME_ID = DSL.field("product_spec_name_id", Long.class);



    public List<CategoriesAndSpecsDto> getAllCategories(boolean cacheable) {
        stopWatch.start();
        Map<Long, Specs> specsMap = new HashMap<>();
        Map<Long, CategoriesAndSpecsDto> categoriesMap = new HashMap<>();
        this.entityManager.createQuery("select " +
                        "c.id," +
                        "c.nameOfCategory," +
                        "sn.id," +
                        "sn.name," +
                        "sv.id," +
                        "sv.value " +
                        "from product_category c " +
                        "join c.productSpecNames sn " +
                        "join sn.productSpecValues sv")
                .unwrap(Query.class)
//                .setMaxResults(1000)
                .setHint(QueryHints.HINT_CACHEABLE, cacheable)
                .setTupleTransformer((tuples, aliases) -> {
                    Long categoryId = (Long) tuples[0];
                    Long specId = (Long) tuples[2];

                    Specs spec = specsMap.computeIfAbsent(specId, k -> new Specs(specId, (String) tuples[3]));
                    spec.getProductSpecValues().add(new SpecValue((Long) tuples[4], (String) tuples[5], specId));
                    CategoriesAndSpecsDto category =
                            categoriesMap.computeIfAbsent(categoryId, k -> new CategoriesAndSpecsDto(categoryId, (String) tuples[1]));
                    if (!category.getSpecsList().contains(spec)) {
                        category.getSpecsList().add(spec);
                    }
                    /////////////////////////////////////

                    //                    categoriesMap.computeIfAbsent(categoryId,k->
//                                    new CategoriesAndSpecsDto(categoryId,(String) tuples[1]))
//                            .getSpecsMap().putIfAbsent(spec.getId(),spec);
                    return null;
                }).getResultList();

//        categoriesMap.values().forEach(c->{
//            c.setSpecsList(new ArrayList<>(c.getSpecsMap().values()));
//        });

        stopWatch.stop();
        System.out.println("time=" + stopWatch.getTotalTimeMillis());

        return new ArrayList<>(categoriesMap.values());
    }



    public List<CategoriesAndSpecsDto> getAllCategories() {
        stopWatch.start();
        Map<Long, Specs> specsMap = new HashMap<>();
        Map<Long, CategoriesAndSpecsDto> categoriesMap = new HashMap<>();
        Set<Specs> specsSet = new HashSet<>();

        this.entityManager.createQuery("select new org.example.shopservicelayer.dto.CategoriesAndSpecsDto(" +
                        "c.id,c.nameOfCategory  " +
                        ") " +
                        "from product_category c "

                      ,CategoriesAndSpecsDto.class )


//                .setMaxResults(1000)
                .setHint(QueryHints.HINT_CACHEABLE, true)
                .getResultList();


///////////////////////////////

//        categoriesMap.values().forEach(c->{
//            c.setSpecsList(new ArrayList<>(c.getSpecsMap().values()));
//        });

        stopWatch.stop();
        System.out.println("time=" + stopWatch.getTotalTimeMillis());

//        return categoriesMap.values().stream().toList();
        return new ArrayList<>(categoriesMap.values());
    }






//    public List<ProductCategory> getCategories(boolean cacheable) {

//    return
//            ctx.select(
//                    CATEGORY_ID,
//                    CATEGORY_NAME,
//                    PRODUCT_CATEGORY.field("sort_value", Long.class),
//                    multiset(
//                            select(
//                                    SPEC_NAME_ID,
//                                    SPEC_NAME,
//                                    PRODUCT_SPEC_NAME.field("sort_value", Long.class),
//                                    multiset(
//                                            select(
//                                                    SPEC_VALUE_ID,
//                                                    SPEC_VALUE
//                                            )
//                                                    .from(PRODUCT_SPEC_VALUE)
//                                                    .innerJoin(PRODUCT_SPEC_NAME).on(VALUE_SPEC_NAME_ID.eq(SPEC_NAME_ID))
//                                                    .where(VALUE_SPEC_NAME_ID.eq(SPEC_NAME_ID))
//                                    ).as("productSpecValues").convertFrom(r -> r.map(Records.mapping(ProductSpecsValue::new)))
//                            )
//                                    .from(PRODUCT_SPEC_NAME)
//                                    .innerJoin(PRODUCT_CATEGORY).on(SPEC_NAME_CATEGORY_ID.eq(CATEGORY_ID))
//                    ).as("productSpecNames").convertFrom(r -> r.map(Records.mapping(ProductSpecName::new)))
//            )
//            .from(PRODUCT_CATEGORY)
//            .fetch(String.valueOf(Records.mapping(ProductCategory::new)));

//        return null;
//    }





}
