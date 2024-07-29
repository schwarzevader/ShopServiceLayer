package org.example.shopservicelayer.repositories.jpaInterfaces;




import org.example.shopservicelayer.dto.ProductDTO;
import org.example.shopservicelayer.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {



    Optional<Product> findById(Long id);



    @Query(value = """
            select  new org.example.shopservicelayer.dto.ProductDTO(
                p.id ,
                p.name ,
                p.description,
                p.price,
                 p.rating,
                 p.shadowRating )
            from  products p
            join p.productCategory  pc
            where pc.id =:idC
             """)
    Page<ProductDTO> getProductsByProductCategoryId(@Param("idC") Long id , Pageable pageable);


    @Query(value = """
            select  new org.example.shopservicelayer.dto.ProductDTO(
                p.id ,
                p.name ,
                p.description,
                p.price,
                 p.rating,
                 p.shadowRating )
            from  products p
            where p.id in:idL
             """)
    Page<ProductDTO> compareProductsByProductsListID(@Param("idL") List<Long> idList , Pageable pageable);

    Product findProductByName(String name);

    Product findProductById(Long id);

    boolean existsProductById(Long id);

//    @Query("SELECT new org.example.jpatest.dto.PersonDto(p.id, CONCAT(p.firstName, ' ', p.lastName)) FROM Person p WHERE p.age >= ?1 AND p.age <= ?2 ORDER BY p.lastName ASC")
//    Page<PersonDto> findPeopleByAgeRange(int minAge, int maxAge, Pageable pageable);


//product DTO

//    @Query(value = """
//            select new ProductDto(
//                p.id as id,
//                p.name as name,
//                p.description as description,
//                p.price as price,
//                c.companyName as companyName
//                )
//            from Product p
//            join p.category c
//            where p.name like :postTitle
//            order by p.id
//            """)
//    List<ProductDto> findCommentDTOByTitle(
//            @Param("postTitle") String postTitle
//    );


//    @Query(value = """
//            select p
//            from Product p
//            join p.category c
//            where CONCAT(p.category.nameOfCategory ,' ',p.name ) LIKE %?1%
//            order by p.price desc """)
//    Page<Product> findProductByNameOrCategoryOrCompany(Pageable pageable, String product);


    //WHERE p.category.nameOfCategory LIKE:categoryName or p.name LIKE:productName


//    @Query(value = """
//            select  new org.example.jpatest.dto.ProductDTO(
//                p.id as id,
//                p.name as name,
//                p.description as description,
//                p.price as price,
//                c.nameOfCategory as nameOfcategory)
//            from Product p
//            join p.category c
//            where CONCAT(p.category.nameOfCategory ,' ',p.name ) LIKE %?1%
//
//            order by p.price desc """)
//    Page<ProductDTO> findProductByNameOrCategoryOrCompany(Pageable pageable, String productName );

/////////////////////////
//
//    @Query(value = """
//            select  new org.example.jpatest.dto.ProductDTO(
//                p.id as id,
//                p.name as name,
//                p.description as description,
//                p.price as price,
//                c.nameOfCategory as nameOfcategory)
//            from Product p
//            join p.category c
//            join p.company comp
//            where CONCAT(p.category.nameOfCategory ,' ',p.name,' ',p.company.companyName ) LIKE %?1%
//
//            order by p.price desc """)
//    Page<ProductDTO> findProductByNameOrCategoryOrCompany(Pageable pageable, String productName );
//
//
////    @Query(value = """
////            select new org.example.jpatest.dto.ProductDTO(
////                p.id as id,
////                p.name as name,
////                p.description as description,
////                p.price as price,
////                c.nameOfCategory as nameOfcategory)
////            from Product p
////            join p.category c
////            where CONCAT(p.name , p.category.nameOfCategory ,p.category.companyName) LIKE %?1%
////            order by p.price desc """)
////    List<ProductDTO> findProductByNameOrCategoryOrCompany(Pageable pageable,String postTitle);
//
////    @Query("SELECT p FROM Product p WHERE p.name LIKE %?1%"
////            + " OR p.category.nameOfCategory LIKE %?1%"
////            + " OR p.category.companyName LIKE %?1%")
//    @Query(value = "SELECT p FROM Product p WHERE CONCAT(p.name , p.category.nameOfCategory ,p.company.companyName) LIKE %?1%")
//    public List<Product> findProductByNameOrCategory(String keyword, Pageable pageable);
//
//    //public List<Product> paginationProductList(int firstResult , int maxPageResult);
//

//


}
