package org.example.shopservicelayer.repositories.imp;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.shopservicelayer.dto.SpecValue;
import org.example.shopservicelayer.dto.Specs;
import org.example.shopservicelayer.entity.ProductSpecsValue;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

//@Repository
@Service
@Transactional
public class SpecValueRepoImp {

    @PersistenceContext
    private EntityManager entityManager;

    public void add(Long children, Long parent) {
        this.entityManager.getReference(ProductSpecsValue.class, children)
                .addParent(this.entityManager.getReference(ProductSpecsValue.class, parent));
    }




    public List<Specs> getSpecsParentsAndValues(Long id) {

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
                    System.out.println("0===" + tuples[0]);
                    System.out.println("1===" + tuples[1]);
                    System.out.println("2===" + tuples[2]);

                    Long specId = (Long) tuples[2];
                    specsMap.computeIfAbsent(specId, k -> new Specs(specId, (String) tuples[3]))
                            .getProductSpecValues().add(new SpecValue((Long) tuples[0], (String) tuples[1], specId));
                    return null;
                }).getResultList();
        return new ArrayList<>(specsMap.values());
    }

    public List<Specs> getSpecsChildrensValues(Long id) {

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
                    System.out.println("0===" + tuples[0]);
                    System.out.println("1===" + tuples[1]);
                    System.out.println("2===" + tuples[2]);

                    Long specId = (Long) tuples[2];
                    specsMap.computeIfAbsent(specId, k -> new Specs(specId, (String) tuples[3]))
                            .getProductSpecValues().add(new SpecValue((Long) tuples[0], (String) tuples[1], specId));
                    return null;
                }).getResultList();
        return new ArrayList<>(specsMap.values());
    }

    public List<Specs> getSpecsParentsAndChildrensValues(List<Long> id) {
        Map<Long, Specs> specsMap = new HashMap<>();

        this.entityManager.createQuery(
                        "WITH specValueParentsHierarchy AS (" +
                                "SELECT psv.parents svh " +
                                "FROM productSpecValue psv " +
//                                "WHERE psv.id = :id " +
                                "WHERE psv.id in :id " +
                                "UNION ALL " +
                                "SELECT psv2.parents svh2 " +
                                "FROM productSpecValue psv2 " +
                                "JOIN specValueParentsHierarchy svph ON psv2 = svph.svh" +
                                "), " +
                                "specValueChildrenHierarchy AS (" +
                                "SELECT psv.childrens svh " +
                                "FROM productSpecValue psv " +
//                                "WHERE psv.id = :id " +
                                "WHERE psv.id in :id " +
                                "UNION ALL " +
                                "SELECT psv2.childrens svh2 " +
                                "FROM productSpecValue psv2 " +
                                "JOIN specValueChildrenHierarchy svch ON psv2 = svch.svh" +
                                ") " +
                                "SELECT " +
                                "svph.svh.id, " +
                                "svph.svh.value, " +
                                "svph.svh.productSpecName.id, " +
                                "svph.svh.productSpecName.name " +
                                "FROM specValueParentsHierarchy svph " +
                                "UNION " +
                                "SELECT " +
                                "svch.svh.id, " +
                                "svch.svh.value, " +
                                "svch.svh.productSpecName.id, " +
                                "svch.svh.productSpecName.name " +
                                "FROM specValueChildrenHierarchy svch")
                .setParameter("id", id).unwrap(Query.class)
                .setResultTransformer((tuples, aliases) -> {
                    System.out.println("--------------------------------");
                    System.out.println("0===" + tuples[0]);
                    System.out.println("1===" + tuples[1]);
                    System.out.println("2===" + tuples[2]);
                    System.out.println("3===" + tuples[3]);


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


        return new ArrayList<>(specsMap.values());
    }


}
