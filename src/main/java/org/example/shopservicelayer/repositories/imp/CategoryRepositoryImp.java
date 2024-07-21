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
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
//@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRepositoryImp {

    @PersistenceContext
    private EntityManager entityManager;

    private int i = 0;
    StopWatch stopWatch = new StopWatch();
    private SpecsDtoTransformer specsDtoTransformer;


    public List<CategoriesAndSpecsDto> getAllCategories(){
        stopWatch.start();
        Map<Long, Specs> specsMap = new HashMap<>();
        Map<Long, CategoriesAndSpecsDto> categoriesMap = new HashMap<>();
        Set<Specs> specsSet= new HashSet<>();

        this.entityManager.createQuery("select " +
                        "c.id,c.nameOfCategory," +
                        "sn.id,sn.name," +
                        "sv.id,sv.value " +
                        "from product_category c " +
                        "join c.productSpecNames sn " +
                        "join sn.productSpecValues sv")
                .unwrap(Query.class)
                .setResultTransformer((tuples, aliases) -> {
//                    System.out.println("--------------------------------");
//                    System.out.println("0===" + tuples[0]);
//                    System.out.println("1===" + tuples[1]);
//                    System.out.println("2===" + tuples[2]);
//                    System.out.println("3===" + tuples[3]);
//                    System.out.println("4===" + tuples[4]);
//                    System.out.println("5===" + tuples[5]);
                    Long categoryId = (Long) tuples[0];
                    Long specId = (Long) tuples[2];
                    //////////////////////////////

                    Specs spec = specsMap.computeIfAbsent(specId, k -> new Specs(specId, (String) tuples[3]));
                    spec.getProductSpecValues().add(new SpecValue((Long) tuples[4], (String) tuples[5], specId));
                    categoriesMap.computeIfAbsent(categoryId,k->
                                    new CategoriesAndSpecsDto(categoryId,(String) tuples[1]))
                            .getSpecsMap().putIfAbsent(spec.getId(),spec);


/////////////////////////////////////////


//                    Long specValueId = (Long) tuples[4];
//                    Specs spec = specsMap.computeIfAbsent(specId, k -> new Specs(specId, (String) tuples[3]));
//                    spec.getProductSpecValues().add(new SpecValue(specValueId, (String) tuples[5], specId));
//                    CategoriesAndSpecsDto category =
//                            categoriesMap.computeIfAbsent(categoryId, k -> new CategoriesAndSpecsDto(categoryId, (String) tuples[1]))
//                            ;
//                    if (!category.getSpecsList().contains(spec)) {
//                        category.getSpecsList().add(spec);
//                    }

                    /////////////////////////////////////
                    return null;
                }).getResultList();


///////////////////////////////

        categoriesMap.values().forEach(c->{
            c.setSpecsList(new ArrayList<>(c.getSpecsMap().values()));
        });

        stopWatch.stop();
        System.out.println("time="+stopWatch.getTotalTimeMillis());

//        return categoriesMap.values().stream().toList();
        return new ArrayList<>(categoriesMap.values());
    }


//
//    @Autowired
//    private ProductCategoryRepo crudCategory;
//
//    public void saveCategory(ProductCategory category){
//        crudCategory.save(category);
//    }
//
//    public void deleteCategory(Long id){
//        crudCategory.deleteById(id);
//    }
//    @SuppressWarnings("unchecked")
//    public List<String> paginationCategoryDTO(){
//        return entityManager.createQuery
//                        ("select c.nameOfCategory from ProductCategory c ")
//                .getResultList();
//    }
//
//
//    public List<String> getCategoryNameByTypeCategory(String name){
//        return crudCategory.getCategoriesNameByTypeCategory(name);
//    }
//


}
