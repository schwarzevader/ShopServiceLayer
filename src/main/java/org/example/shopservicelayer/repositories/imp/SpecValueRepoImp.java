package org.example.shopservicelayer.repositories.imp;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.shopservicelayer.dto.SpecValueParent;
import org.example.shopservicelayer.entity.ProductSpecsValue;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//@Repository
@Service
@Transactional
public class SpecValueRepoImp {

    @PersistenceContext
    private EntityManager entityManager;


    public void addChildren(Long childrenId, Long parentId) {

//        Session session = this.entityManager.unwrap(Session.class);
//        session.beginTransaction();
//        ProductSpecsValue parent = session.find(ProductSpecsValue.class,parentId);
//        ProductSpecsValue child = session.find(ProductSpecsValue.class,childrenId);
        ProductSpecsValue parent = this.entityManager.find(ProductSpecsValue.class, parentId);
        ProductSpecsValue child = this.entityManager.find(ProductSpecsValue.class, childrenId);


//        parent.addChildren(child);
//        this.entityManager.persist(parent);

        child.setSpecValueParent(parent);
        this.entityManager.persist(child);
//        session.persist(parent);
//        session.getTransaction().commit();
//        session.close();
    }


    public ProductSpecsValue findById(Long id) {
        return this.entityManager.createQuery(
                "select s " +
                        "from  productSpecValue s " +
                        "where s.id  = :id", ProductSpecsValue.class).setParameter("id", id).getSingleResult();
    }




//////////////////////    /////////////////////////////

    public List<SpecValueParent> getSpecValueParent(Long id) {


//        "WITH specValueChildrenHierarchy AS (" +
//                                "SELECT sv.children sv  " +
//                                "from productSpecValue sv " +
//                                "where sv.id = :id " +
//                                "UNION ALL " +
//                                "SELECT sv.children sv " +
//                                "FROM productSpecValue sv " +
//                                "JOIN  specValueChildrenHierarchy svch on sv = svch.sv " +
//                                "ORDER BY sv.id )" +
//                                "SELECT new org.example.shopservicelayer.dto.SpecValueParent(" +
//                                "svch.sv.id, " +
//                                "svch.sv.value," +
//                                "svch.sv.children" +
//                                ")" +
//                                " from specValueChildrenHierarchy svch", SpecValueParent.class)

        return entityManager.createNativeQuery(
                        "WITH RECURSIVE SpecValueHierarchy AS (" +
                                "    SELECT psv.product_spec_value_id AS id, " +
                                "           psv.value, " +
                                "           psv.spec_value_parent_id AS parent_id, " +
                                "           CAST(psv.product_spec_value_id AS CHAR(255)) AS path " +
                                "    FROM product_spec_value psv " +
                                "    WHERE psv.product_spec_value_id = :id " +
                                "    UNION ALL " +
                                "    SELECT psv.product_spec_value_id AS id, " +
                                "           psv.value, " +
                                "           psv.spec_value_parent_id AS parent_id, " +
                                "           CONCAT(sph.path, ',', psv.product_spec_value_id) " +
                                "    FROM product_spec_value psv " +
                                "    INNER JOIN SpecValueHierarchy sph ON sph.id = psv.spec_value_parent_id" +
                                ") " +
                                "SELECT id, value, parent_id " +
                                "FROM SpecValueHierarchy " +
                                "ORDER BY path"
                )
                .setParameter("id", id).unwrap(Query.class)
                .setResultTransformer((tuples, aliases) -> {
                    return new SpecValueParent((Long) tuples[0], (String) tuples[1], (Long) tuples[2]);
                }).getResultList();

    }

    //////////////////////////////////

    private List<ProductSpecsValue> findChildren(Long parentId, List<Object[]> results) {
        return results.stream()
                .filter(row -> row[2] != null && ((Number) row[2]).longValue() == parentId)
                .map(row -> new ProductSpecsValue(
                        findChildren(((Long) row[0]).longValue(), results),
                        ((Long) row[0]).longValue(),
                        (String) row[1],
                        row[2] != null ? ((Number) row[2]).longValue() : null
                ))
                .collect(Collectors.toList());
    }


    public List<SpecValueParent> getSpecValueParentt(Long id) {

//        Session session = this.entityManager.unwrap(Session.class);
//        session.beginTransaction();
//        return session.createQuery(


        return this.entityManager.createQuery("""
    WITH specValueHierarchy AS (
      SELECT sv.valueId, sv.value, sv.specValueParent.valueId as parentId
      FROM productSpecValue sv
      WHERE sv.valueId = :id

      UNION ALL

      SELECT sv.valueId, sv.value, sv.specValueParent.valueId as parentId
      FROM productSpecValue sv
      JOIN specValueHierarchy svh ON sv. = svh.parentId
    )
    SELECT new org.example.shopservicelayer.dto.SpecValueParent(
        svh.valueId,
        svh.value,
        svh.parentId
    )
    FROM specValueHierarchy svh
    """, SpecValueParent.class)
                .setParameter("id", id)
                .getResultList();
    }


//    public List<SpecValueParent> getSpecValueParentt(Long id){
//
////        Session session = this.entityManager.unwrap(Session.class);
////        session.beginTransaction();
////        return session.createQuery(
//        return this.entityManager.createQuery(
//                        "WITH specValueChildrenHierarchy AS (" +
//                                "SELECT sv.children sv  " +
//                                "from productSpecValue sv " +
//                                "where sv.id = :id " +
//                                " " +
//                                "UNION ALL " +
//                                " " +
//                                "SELECT sv.children sv " +
//                                "FROM productSpecValue sv " +
//                                "JOIN  specValueChildrenHierarchy svch on sv.id = svch.sv.id " +
//                                "ORDER BY sv.id )" +
//                                "SELECT new org.example.shopservicelayer.dto.SpecValueParent(" +
//                                "svch.sv.id, " +
//                                "svch.sv.value," +
//                                "svch.sv.children" +
//                                ")" +
//                                " from specValueChildrenHierarchy svch", SpecValueParent.class)
//                .setParameter("id",id).getResultList();
//    }


//    public List<SpecValueParent> getSpecValueParent(List<Long> id) {
//        return null;
//    }


    public void save(ProductSpecsValue apple) {
        this.entityManager.persist(apple);
    }
}
