package org.example.shopservicelayer.repositories.imp;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.shopservicelayer.dto.CategoriesAndSpecsDto;
import org.example.shopservicelayer.dto.SpecValue;
import org.example.shopservicelayer.dto.Specs;
import org.example.shopservicelayer.dtoTransformer.dtoTransormInterfaces.SpecsDtoTransformer;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
//@RequiredArgsConstructor
@AllArgsConstructor
public class CategoryRepositoryImp {

    @PersistenceContext
    private EntityManager entityManager;


    private SpecsDtoTransformer specsDtoTransformer;


    public List<CategoriesAndSpecsDto> getAllCategories(){
        Map<Long, Specs> specsMap = new HashMap<>();
        Map<Long, CategoriesAndSpecsDto> categoriesMap = new HashMap<>();

        this.entityManager.createQuery("select " +
                        "c.id,c.nameOfCategory," +
                        "sn.id,sn.name," +
                        "sv.id,sv.value " +
                        "from product_category c " +
                        "join c.productSpecNames sn " +
                        "join sn.productSpecValues sv")
                .unwrap(Query.class)
                .setResultTransformer((tuples, aliases) -> {
                    System.out.println("--------------------------------");
                    System.out.println("0===" + tuples[0]);
                    System.out.println("1===" + tuples[1]);
                    System.out.println("2===" + tuples[2]);
//map category ?
//                    specsDtoTransformer.specsTransform(specsMap,
//                            (Long)tuples[2],(String)tuples[3],(Long)tuples[4],(String)tuples[5]);
                    Long categoryId = (Long) tuples[0];
                    categoriesMap.computeIfAbsent(categoryId,k->
                                    new CategoriesAndSpecsDto(categoryId,(String) tuples[1]))
                            .setSpecsList(specsDtoTransformer.specsTransform(specsMap,
                                    (Long)tuples[2],(String)tuples[3],(Long)tuples[4],(String)tuples[5]));

//                    Long specId = (Long) tuples[2];
//                    specsMap.computeIfAbsent(specId, k -> new Specs(specId, (String) tuples[3]))
//                            .getProductSpecValues().add(new SpecValue((Long) tuples[4], (String) tuples[5], specId));
                    return null;
                }).getResultList();


        return categoriesMap.values().stream().toList();
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
