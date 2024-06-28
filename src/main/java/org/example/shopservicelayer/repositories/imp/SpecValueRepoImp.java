package org.example.shopservicelayer.repositories.imp;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.shopservicelayer.dto.SpecValueParent;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SpecValueRepoImp {

    @PersistenceContext
    private EntityManager entityManager;


//    entityManager.createQuery("""
//    WITH postCommentChildHierarchy AS (
//      SELECT pc.children pc
//      FROM PostComment pc
//      WHERE pc.id = :commentId
//⠀
//      UNION ALL
//⠀
//      SELECT pc.children pc
//      FROM PostComment pc
//      JOIN postCommentChildHierarchy pch ON pc = pch.pc
//      ORDER BY pc.id
//    )
//    SELECT new PostCommentRecord(
//        pch.pc.id,
//        pch.pc.createdOn,
//        pch.pc.review,
//        pch.pc.score,
//        pch.pc.parent.id
//    )
//    FROM postCommentChildHierarchy pch
//    """, PostCommentRecord.class)
//            .setParameter("commentId", 1L)
//.getResultList();

    public List<SpecValueParent> getSpecValueParent(Long id){


        return this.entityManager.createQuery(
                "WITH specValueChildrenHierarchy AS (" +
                        "SELECT sv.children sv  " +
                        "from productSpecValue sv " +
                        "where sv.id = :id " +
                        " " +
                        "UNION ALL " +
                        " " +
                        "SELECT sv.children sv " +
                        "FROM productSpecValue sv " +
                        "JOIN specValueChildrenHierarchy svch on sv = svch.sv " +
                        "ORDER BY sv.id )" +
                        "SELECT new org.example.shopservicelayer.dto.SpecValueParent(" +
                        "svch.sv.id, " +
                        "svch.sv.value," +
                        "svch.sv.children" +
                        ")" +
                        " from specValueChildrenHierarchy svch", SpecValueParent.class)
                .setParameter("id",id).getResultList();
    }


    public List<SpecValueParent> getSpecValueParent(List <Long> id){
        return null;
    }

    
}
