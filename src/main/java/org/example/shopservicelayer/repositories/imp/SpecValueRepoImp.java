package org.example.shopservicelayer.repositories.imp;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.shopservicelayer.dto.SpecValue;
import org.example.shopservicelayer.dto.Specs;
import org.example.shopservicelayer.entity.ProductSpecsValue;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

//@Repository
@Service
@Transactional
public class SpecValueRepoImp {

    @PersistenceContext
    private EntityManager entityManager;

    public void add( Long children,Long parent) {
        this.entityManager.find(ProductSpecsValue.class,children)
                .addParent(this.entityManager.find(ProductSpecsValue.class,parent));
    }


    public List<Specs> getSpecsParentsAndValues(Long id){

        Map<Long, Specs> specsMap = new HashMap<>();
        this.entityManager.createQuery(
                        "WITH specValueChildrenHierarchy AS (" +
                                "SELECT psv.parents svh  " +
                                "FROM productSpecValue psv " +
                                "WHERE psv.id = :id " +
                                "UNION ALL " +
                                "SELECT psv2.parents svh2 " +
                                "FROM productSpecValue psv2 " +

                                "JOIN specValueChildrenHierarchy svch ON psv2 = svch.svh" +
                                ") " +
                                "SELECT" +
                                " svch.svh.id, " +
                                "svch.svh.value, " +
                                "svch.svh.productSpecName.id," +
                                "svch.svh.productSpecName.name " +
                                " FROM specValueChildrenHierarchy svch")
                .setParameter("id", id).unwrap(Query.class)
                .setResultTransformer((tuples, aliases) -> {
                    System.out.println("--------------------------------");
                    System.out.println("0==="+tuples[0]);
                    System.out.println("1==="+tuples[1]);
                    System.out.println("2==="+tuples[2]);

                    Long specId= (Long) tuples[2];
                    specsMap.computeIfAbsent(specId, k -> new Specs(specId, (String) tuples[3]))
                            .getProductSpecValues().add(new SpecValue( (Long) tuples[0],(String) tuples[1],  specId));
                    return  null;
                }).getResultList();
        return specsMap.values().stream().toList();
    }

        public List<Specs> getSpecsChildrensValues(Long id){

        Map<Long, Specs> specsMap = new HashMap<>();
        this.entityManager.createQuery(
                        "WITH specValueChildrenHierarchy AS (" +
                                "SELECT psv.childrens svh  " +
                                "FROM productSpecValue psv " +
                                "WHERE psv.id = :id " +
                                "UNION ALL " +
                                "SELECT psv2.childrens svh2 " +
                                "FROM productSpecValue psv2 " +

                                "JOIN specValueChildrenHierarchy svch ON psv2 = svch.svh" +
                                ") " +
                                "SELECT" +
                                " svch.svh.id, " +
                                "svch.svh.value, " +
                                "svch.svh.productSpecName.id," +
                                "svch.svh.productSpecName.name " +
                                " FROM specValueChildrenHierarchy svch")
                .setParameter("id", id).unwrap(Query.class)
                .setResultTransformer((tuples, aliases) -> {
                    System.out.println("--------------------------------");
                    System.out.println("0==="+tuples[0]);
                    System.out.println("1==="+tuples[1]);
                    System.out.println("2==="+tuples[2]);

                    Long specId= (Long) tuples[2];
                    specsMap.computeIfAbsent(specId, k -> new Specs(specId, (String) tuples[3]))
                            .getProductSpecValues().add(new SpecValue( (Long) tuples[0],(String) tuples[1],  specId));
                    return  null;
                }).getResultList();
        return specsMap.values().stream().toList();
    }

    public List<Specs> getSpecsParentsAndChildrensValues(Long id) {
        Map<Long, Specs> specsMap = new HashMap<>();
//        this.entityManager.createQuery(
//                        "WITH specValueHierarchy AS (" +
//                                "SELECT psv.parents AS svh, psv.childrens AS svh2 " +
//                                "FROM productSpecValue psv " +
//                                "WHERE psv.id = :id " +
//                                "UNION ALL " +
//                                "SELECT psv2.parents AS svh, psv2.childrens AS svh2 " +
//                                "FROM productSpecValue psv2 " +
//                                "JOIN specValueHierarchy svh ON psv2 = svh.svh " +
//                                "JOIN specValueHierarchy svh2 ON psv2 = svh2.svh2" +
//                                ") " +
//                                "SELECT " +
//                                "svh.svh.id, svh.svh.value, svh.svh.productSpecName.id, svh.svh.productSpecName.name, " +
//                                "svh2.svh2.id, svh2.svh2.value, svh2.svh2.productSpecName.id, svh2.svh2.productSpecName.name " +
//                                "FROM specValueHierarchy svh " +
//                                "JOIN specValueHierarchy svh2 ON svh.svh.id = svh2.svh2.id")
//                .setParameter("id", id).unwrap(Query.class)
//                .setResultTransformer((tuples, aliases) -> {
//                    System.out.println("--------------------------------");
//                    System.out.println("0===" + tuples[0]);
//                    System.out.println("1===" + tuples[1]);
//                    System.out.println("2===" + tuples[2]);
//                    System.out.println("3===" + tuples[3]);
//                    System.out.println("4===" + tuples[4]);
//                    System.out.println("5===" + tuples[5]);
//                    System.out.println("6===" + tuples[6]);
//                    System.out.println("7===" + tuples[7]);
//
//                    Long specId = (Long) tuples[2];
//                    specsMap.computeIfAbsent(specId, k -> new Specs(specId, (String) tuples[3]))
//                            .getProductSpecValues().add(new SpecValue((Long) tuples[0], (String) tuples[1], specId));
//                    specsMap.computeIfAbsent(specId, k -> new Specs(specId, (String) tuples[7]))
//                            .getProductSpecValues().add(new SpecValue((Long) tuples[4], (String) tuples[5], specId));
//                    return null;
//                }).getResultList();
//        return specsMap.values().stream().toList();

        this.entityManager.createQuery(
                        "WITH specValueParentsHierarchy AS (" +
                                "SELECT psv.parents svh " +
                                "FROM productSpecValue psv " +
                                "WHERE psv.id = :id " +
                                "UNION ALL " +
                                "SELECT psv2.parents svh2 " +
                                "FROM productSpecValue psv2 " +
                                "JOIN specValueParentsHierarchy svph ON psv2 = svph.svh" +
                                "), " +
                                "specValueChildrenHierarchy AS (" +
                                "SELECT psv.childrens svh " +
                                "FROM productSpecValue psv " +
                                "WHERE psv.id = :id " +
                                "UNION ALL " +
                                "SELECT psv2.childrens svh2 " +
                                "FROM productSpecValue psv2 " +
                                "JOIN specValueChildrenHierarchy svch ON psv2 = svch.svh" +
                                ") " +
                                "SELECT " +
                                "svph.svh.id, svph.svh.value, svph.svh.productSpecName.id, svph.svh.productSpecName.name " +
                                "FROM specValueParentsHierarchy svph " +
                                "UNION " +
                                "SELECT " +
                                "svch.svh.id, svch.svh.value, svch.svh.productSpecName.id, svch.svh.productSpecName.name " +
                                "FROM specValueChildrenHierarchy svch")
                .setParameter("id", id).unwrap(Query.class)
                .setResultTransformer((tuples, aliases) -> {
                    System.out.println("--------------------------------");
                    System.out.println("0===" + tuples[0]);
                    System.out.println("1===" + tuples[1]);
                    System.out.println("2===" + tuples[2]);

                    Long specId = (Long) tuples[2];
                    specsMap.computeIfAbsent(specId, k -> new Specs(specId, (String) tuples[3]))
                            .getProductSpecValues().add(new SpecValue((Long) tuples[0], (String) tuples[1], specId));
                    return null;
                }).getResultList();


        ////////////////////////////////////////////////
//        this.entityManager.createQuery(
//                        "WITH specValueChildrenHierarchy AS (" +
//                                "SELECT psv.parents svh  " +
//                                "FROM productSpecValue psv " +
//                                "WHERE psv.id = :id " +
//                                "UNION ALL " +
//                                "SELECT psv2.parents svh2 " +
//                                "FROM productSpecValue psv2 " +
//
//                                "JOIN specValueChildrenHierarchy svch ON psv2 = svch.svh" +
//                                ") " +
//                                "SELECT" +
//                                " svch.svh.id, " +
//                                "svch.svh.value, " +
//                                "svch.svh.productSpecName.id," +
//                                "svch.svh.productSpecName.name " +
//                                " FROM specValueChildrenHierarchy svch")
//                .setParameter("id", id).unwrap(Query.class)
//                .setResultTransformer((tuples, aliases) -> {
//                    System.out.println("parents --------------------------------");
//                    System.out.println("0==="+tuples[0]);
//                    System.out.println("1==="+tuples[1]);
//                    System.out.println("2==="+tuples[2]);
//
//                    Long specId= (Long) tuples[2];
//                    specsMap.computeIfAbsent(specId, k -> new Specs(specId, (String) tuples[3]))
//                            .getProductSpecValues().add(new SpecValue( (Long) tuples[0],(String) tuples[1],  specId));
//                    return  null;
//                }).getResultList();
//
//        this.entityManager.createQuery(
//                        "WITH specValueChildrenHierarchy AS (" +
//                                "SELECT psv.childrens svh  " +
//                                "FROM productSpecValue psv " +
//                                "WHERE psv.id = :id " +
//                                "UNION ALL " +
//                                "SELECT psv2.childrens svh2 " +
//                                "FROM productSpecValue psv2 " +
//
//                                "JOIN specValueChildrenHierarchy svch ON psv2 = svch.svh" +
//                                ") " +
//                                "SELECT" +
//                                " svch.svh.id, " +
//                                "svch.svh.value, " +
//                                "svch.svh.productSpecName.id," +
//                                "svch.svh.productSpecName.name " +
//                                " FROM specValueChildrenHierarchy svch")
//                .setParameter("id", id).unwrap(Query.class)
//                .setResultTransformer((tuples, aliases) -> {
//                    System.out.println("childrens --------------------------------");
//                    System.out.println("0==="+tuples[0]);
//                    System.out.println("1==="+tuples[1]);
//                    System.out.println("2==="+tuples[2]);
//
//                    Long specId= (Long) tuples[2];
//                    specsMap.computeIfAbsent(specId, k -> new Specs(specId, (String) tuples[3]))
//                            .getProductSpecValues().add(new SpecValue( (Long) tuples[0],(String) tuples[1],  specId));
//                    return  null;
//                }).getResultList();


        return specsMap.values().stream().toList();
    }



/////////////////////OneToMane ManyToOne
//    public void addChildren(Long childrenId, Long parentId) {
//        ProductSpecsValue parent = this.entityManager.find(ProductSpecsValue.class, parentId);
//        ProductSpecsValue child = this.entityManager.find(ProductSpecsValue.class, childrenId);
//        child.setSpecValueParent(parent);
//        this.entityManager.persist(child);
//    }
//
//
//
////////////////////////    /////////////////////////////
//
//    public List<SpecValueParent> getSpecValueParent(Long id) {
//
//
//
//        return entityManager.createNativeQuery(
//                        "WITH RECURSIVE SpecValueHierarchy AS (" +
//                                "    SELECT psv.product_spec_value_id AS id, " +
//                                "           psv.value, " +
//                                "           psv.spec_value_parent_id AS parent_id, " +
//                                "           CAST(psv.product_spec_value_id AS CHAR(255)) AS path " +
//                                "    FROM product_spec_value psv " +
//                                "    WHERE psv.product_spec_value_id = :id " +
//                                "    UNION ALL " +
//                                "    SELECT psv.product_spec_value_id AS id, " +
//                                "           psv.value, " +
//                                "           psv.spec_value_parent_id AS parent_id, " +
//                                "           CONCAT(sph.path, ',', psv.product_spec_value_id) " +
//                                "    FROM product_spec_value psv " +
//                                "    INNER JOIN SpecValueHierarchy sph ON sph.id = psv.spec_value_parent_id" +
//                                ") " +
//                                "SELECT id," +
//                                " value, " +
//                                "parent_id " +
//                                "FROM SpecValueHierarchy " +
//                                "ORDER BY path"
//                )
//                .setParameter("id", id).unwrap(Query.class)
//                .setResultTransformer((tuples, aliases) -> {
//                    return new SpecValueParent((Long) tuples[0], (String) tuples[1], (Long) tuples[2]);
//                }).getResultList();
//
//    }
//
//    //////////////////////////////////
//
//    private List<ProductSpecsValue> findChildren(Long parentId, List<Object[]> results) {
//        return results.stream()
//                .filter(row -> row[2] != null && ((Number) row[2]).longValue() == parentId)
//                .map(row -> new ProductSpecsValue(
//                        findChildren(((Long) row[0]).longValue(), results),
//                        ((Long) row[0]).longValue(),
//                        (String) row[1],
//                        row[2] != null ? ((Number) row[2]).longValue() : null
//                ))
//                .collect(Collectors.toList());
//    }
//
//
//
//
//
//    public List<SpecValueParent> getSpecValueParentt(Long id){
//
//
//        return this.entityManager.createQuery(
//                        "WITH specValueChildrenHierarchy AS (" +
//                                "SELECT psv.children svh  " +
//                                "FROM productSpecValue psv " +
//                                "WHERE psv.id = :id " +
//                                "UNION ALL " +
//                                "SELECT psv2.children svh2 " +
//                                "FROM productSpecValue psv2 " +
//                                "JOIN specValueChildrenHierarchy svch ON psv2 = svch.svh" +
//                                ") " +
//                                "SELECT" +
//                                " svch.svh.id, " +
//                                "svch.svh.value, " +
//                                "svch.svh.specValueParent.id " +
//                                "FROM specValueChildrenHierarchy svch")
//                .setParameter("id", id).unwrap(Query.class)
//                .setResultTransformer((tuples, aliases) -> {
//                    System.out.println("--------------------------------");
//                    System.out.print("0"+tuples[0]);
//                    System.out.print("1"+tuples[1]);
//
//                    return  new SpecValueParent((Long) tuples[0], (String) tuples[1], (Long) tuples[2]);
//                }).getResultList();
//
//    }
//
//
//    public List<Specs> getSpecsAndValues(Long id){
//
//        Map<Long, Specs> specsMap = new HashMap<>();
//        this.entityManager.createQuery(
//                        "WITH specValueChildrenHierarchy AS (" +
//                                "SELECT psv.children svh  " +
//                                "FROM productSpecValue psv " +
//                                "WHERE psv.id = :id " +
//                                "UNION ALL " +
//                                "SELECT psv2.children svh2 " +
//                                "FROM productSpecValue psv2 " +
//
//                                "JOIN specValueChildrenHierarchy svch ON psv2 = svch.svh" +
//                                ") " +
//                                "SELECT" +
//                                " svch.svh.id, " +
//                                "svch.svh.value, " +
//                                "svch.svh.productSpecName.id," +
//                                "svch.svh.productSpecName.name " +
//                                " FROM specValueChildrenHierarchy svch")
//                .setParameter("id", id).unwrap(Query.class)
//                .setResultTransformer((tuples, aliases) -> {
//                    System.out.println("--------------------------------");
//                    System.out.println("0==="+tuples[0]);
//                    System.out.println("1==="+tuples[1]);
//                    System.out.println("2==="+tuples[2]);
//
//                    Long specId= (Long) tuples[2];
//                    specsMap.computeIfAbsent(specId, k -> new Specs(specId, (String) tuples[3]))
//                            .getProductSpecValues().add(new SpecValue( (Long) tuples[0],(String) tuples[1],  specId));
//                    return  null;
//                }).getResultList();
//        return specsMap.values().stream().toList();
//    }
//
//
////    public List<SpecValueParent> getSpecValueParent(List<Long> id) {
////        return null;
////    }
//
//
//    public void save(ProductSpecsValue apple) {
//        this.entityManager.persist(apple);
//    }

}
