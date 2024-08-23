package org.example.shopservicelayer.repositories.imp;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.example.shopservicelayer.dto.*;
import org.example.shopservicelayer.entity.Product;
import org.example.shopservicelayer.entity.ProductSpecsValue;
import org.example.shopservicelayer.repositories.jpaInterfaces.ProductRepository;
import org.hibernate.jpa.QueryHints;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
//@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
public class ProductRepositoryImp {

//    Tag misc = entityManager.unwrap( Session.class )
//            .bySimpleNaturalId(Tag.class)
//            .load( "Misc" );
//
//    Post post = entityManager.createQuery(
//                    "select p " +
//                            "from Post p " +
//                            "join fetch p.tags pt " +
//                            "join fetch pt.tag " +
//                            "where p.id = :postId", Post.class)
//            .setParameter( "postId", 1L )
//            .getSingleResult();
//
//post.removeTag( misc );


    StopWatch stopWatch = new StopWatch();
    @PersistenceContext
    private EntityManager entityManager;


//             entityManager
//            .createNativeQuery(
//                    "SELECT * " +
//                            "FROM cluster c " +
//                            "WHERE EXISTS (" +
//                            "   SELECT ct.cluster_id as c_id " +
//                            "   FROM cluster_tag ct " +
//                            "   JOIN tag t ON ct.tag_id = t.id " +
//                            "   WHERE " +
//                            "       c.id = ct.cluster_id AND ( " +
//                            "           (t.tag_name = :tagName1 AND t.tag_value = :tagValue1) OR " +
//                            "           (t.tag_name = :tagName2 AND t.tag_value = :tagValue2) " +
//                            "       )" +
//                            "   GROUP BY ct.cluster_id " +
//                            "   HAVING COUNT(*) = 2 " +
//                            ") ", Cluster.class)
//            .setParameter("tagName1", "Spark")
//            .setParameter("tagValue1", "2.2")
//            .setParameter("tagName2", "Hadoop")
//            .setParameter("tagValue2", "2.7")
//            .getResultList();
//


    public ProductInfoDto getProductInfoDto(Long id) {
//        "p.name," +
//                                        "p.description," +
//                                        "p.price," +
//                                        "p.rating," +
//                                        "psn.id," +
//                                        "psn.name," +
//                                        "psv.id, " +
//                                        "psv.value " +
//                                        "from products p " +
//                                        "join  p.productSpecItemList psi " +
//                                        "join psi.productSpecsValue psv " +
//                                        "join psv.productSpecName psn " +

        this.stopWatch.start();
        Map<Long, Specs> specsMap = new HashMap<>();
        Map<Long, ProductInfoDto> productInfoMap = new HashMap<>();
        this.entityManager.createQuery("select " +
                        "p.name," +
                        "p.description," +
                        "p.price," +
                        "p.rating," +
                        "psn.id," +
                        "psn.name," +
                        "psv.id, " +
                        "psv.value " +
                        "from productSpecItem psi " +
                        "join  psi.product p " +
                        "join psi.productSpecsValue psv " +
                        "join psv.productSpecName psn " +
                        "where p.id =:id"
                ).setParameter("id", id)
                .unwrap(Query.class)
                .setTupleTransformer((tuples, aliases) -> {

                    Long specId = (Long) tuples[4];





/////////////////////////////////////////

                    //    public ProductInfoDto(Long id, String name, String description, double price, double rating, List<Specs> specsList)

                    Specs spec = specsMap.computeIfAbsent(specId, k -> new Specs(specId, (String) tuples[5]));
                    spec.getProductSpecValues().add(new SpecValue((Long) tuples[6], (String) tuples[7], specId));

//                    productInfoMap.computeIfAbsent(id, k -> new ProductInfoDto(
//                            id,
//                            (String) tuples[0],
//                            (String) tuples[1],
//                            (double) tuples[2],
//                            (double) tuples[3]
//                    )).getSpecsList().putIfAbsent(spec.getId(),spec);

                    ProductInfoDto productInfoDto =
                            productInfoMap.computeIfAbsent(id, k -> new ProductInfoDto(
                                    id,
                                    (String) tuples[0],
                                    (String) tuples[1],
                                    (double) tuples[2],
                                    (double) tuples[3]
                            ));
                    if (!productInfoDto.getSpecsList().contains(spec)) {
                        productInfoDto.getSpecsList().add(spec);
                    }

                    /////////////////////////////////////
                    return null;
                }).getResultList();

//        categoriesMap.values().forEach(c->{
//            c.setSpecsList(new ArrayList<>(c.getSpecsMap().values()));
//        });


        this.stopWatch.stop();
        System.out.println("time=" + stopWatch.getTotalTimeMillis());
//        return new ArrayList<>(categoriesMap.values());
        return productInfoMap.get(id);
    }

    public Product getProduct(Long id) {


        return entityManager.createQuery(
                        "SELECT p " +
                                "FROM products p  " +
                                "JOIN FETCH p.productSpecItemList psi " +
                                "JOIN FETCH psi.productSpecsValue " +
                                "JOIN FETCH psi.product " +
                                "WHERE p.id = :id",
                        Product.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public ProductAndSpecsDto getProductAndSpecs(Long id) {


        Map<Long, Specs> specsMap = new HashMap<>();
        ProductAndSpecsDto productAndSpecsDto = new ProductAndSpecsDto();
//        return (ProductAndSpecsDto)
        entityManager.createQuery("""
                        select  p.id,
                        p.name,
                        p.shadowRating,
                        p.price,
                        p.description,
                        p.quantity,
                        pc.id,
                        sv.id,
                        sv.value,
                        sv.productSpecName.id,
                        sv.productSpecName.name   
                        from products p
                        join  p.productSpecItemList svi
                        join  svi.productSpecsValue sv
                        join p.productCategory pc
                        where p.id=:id
                        """)
                .setParameter("id", id).unwrap(Query.class)
                .setResultTransformer((tuples, aliases) -> {
                    Long specId = (Long) tuples[9];
                    specsMap.computeIfAbsent(specId, k -> new Specs(specId, (String) tuples[10]))
                            .getProductSpecValues().add(new SpecValue((Long) tuples[7], (String) tuples[8], specId));
                    productAndSpecsDto.setId((Long) tuples[0]);
                    productAndSpecsDto.setName((String) tuples[1]);
                    productAndSpecsDto.setShadowRating((double) tuples[2]);
                    productAndSpecsDto.setPrice((double) tuples[3]);
                    productAndSpecsDto.setDescription((String) tuples[4]);
                    productAndSpecsDto.setQuantity((int) tuples[5]);
                    productAndSpecsDto.setCategoryID((Long) tuples[6]);
//                    productAndSpecsDto.setSpecsList(specsMap.values().stream().toList());
//                    return new ProductAndSpecsDto(
//                            (Long) tuples[0],
//                            (String) tuples[1],
//                            (double)tuples[2],
//                            (double)tuples[3],
//                            (String) tuples[4],
//                            (int)tuples[5],
//                            (Long)tuples[6],
//                            (List<Specs>) specsMap.values().stream().toList()
//
//                    );
                    return null;
                }).getSingleResult();
        productAndSpecsDto.setSpecsList(specsMap.values().stream().toList());
        return productAndSpecsDto;
    }


    @Autowired
    //@NonNull
    private ProductRepository productRepository;

    //
//    @Autowired
//    private CrudProductRepository crudProductRepository;
//
//    public ProductRepositoryImp(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }
//
    public void saveProduct(Product product) {
//        productRepository.save(product);
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(product);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }

    }

    public void updateProduct(Long id, Product product, List<ProductSpecsValue> removeSpecValue, List<ProductSpecsValue> addSpecValue) {
//        productRepository.save(product);

        productRepository.findProductById(product.getId());
        try {
            entityManager.getTransaction().begin();
//            Product prod =  entityManager.find(Product.class, id);
            Product prod = entityManager.createQuery("select p " +
                    "from products p " +
                    "join  fetch p.productSpecItemList iti" +
                    " where p.id = :id ", Product.class).setParameter("id", id).getSingleResult();
//            Product prod =  new Product();
            prod.setId(id);
            prod.setName(product.getName());
            prod.setDescription(product.getDescription());
            prod.setShadowRating(product.getShadowRating());
            prod.setRating(product.getRating());
            prod.setPrice(product.getPrice());
            prod.setQuantity(product.getQuantity());
            prod.setProductCategory(product.getProductCategory());
            removeSpecValue.forEach(prod::removeSpecValue);
            addSpecValue.forEach(prod::addProductSpecsValue);

/////////////////////////////////////////////////////////////////////
//            List<PostComment> removedComments = new ArrayList<>(
//                    post.getComments()
//            );
//            removedComments.removeAll(comments);
//
//            for(PostComment removedComment : removedComments) {
//                post.removeComment(removedComment);
//            }
//
//            List<PostComment> newComments = new ArrayList<>(comments);
//            newComments.removeAll(post.getComments());
//
//            comments.removeAll(newComments);
//
//            for(PostComment existingComment : comments) {
//                existingComment.setPost(post);
//
//                PostComment mergedComment = entityManager
//                        .merge(existingComment);
//
//                post.getComments().set(
//                        post.getComments().indexOf(mergedComment),
//                        mergedComment
//                );
//            }
//
//            for(PostComment newComment : newComments) {
//                post.addComment(newComment);
//            }
// /////////////////////////////////////////////////////////////////

//            List<ProductSpecItem> removedSpecItems = new ArrayList<>(prod.getProductSpecItemList());
//            removedSpecItems.removeAll(product.getProductSpecItemList());
//            removedSpecItems.stream().forEach(prod::removeProductSpecItem);
//            List<ProductSpecItem> newSpecItems = new ArrayList<>(product.getProductSpecItemList());
//            newSpecItems.removeAll(prod.getProductSpecItemList());
//            product.getProductSpecItemList().removeAll(newSpecItems);
//            product.getProductSpecItemList().stream().forEach(existing->{
//                existing.setProduct(prod);
//                ProductSpecItem mergeProductSpecItem= entityManager.merge(existing);
//                prod.getProductSpecItemList().set(
//                        prod.getProductSpecItemList().indexOf(mergeProductSpecItem)
//                        ,mergeProductSpecItem
//                );
//            });
//
//            newSpecItems.forEach(prod::addProductSpecItem);


//            prod.setProductSpecItemList(newSpecItems);
            entityManager.merge(prod);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }


//
//
//    public void updateProductJpa(Long id,Product product){
//        product.setId(id);
//        productRepository.save(product);
//    }


    //
//    public void saveCategory(ProductCategory category){
//        entityManager.persist(category);
//    }
//
//    public long allProduct(){
//        return productRepository.count();
//    }
//
//    public Product findProductByName(String name){
//        return productRepository.findProductByName(name);
//    }
//
    public Product findById(Long id) {
//      return productRepository.findProductByIdIs(id);
        return entityManager.find(Product.class, id);
    }
//
//
//
//    public List<ProductDTO> searchAndPaginationProductDTOOrderByPrice(String keyWord, int firstResult ,
//                                                                      int  maxResult  , String descOrAsc ){
//        @SuppressWarnings("unchecked")
//        List<ProductDTO> productDTOList =  entityManager
//                .createQuery(
//                        "select new " +
//                                "   com.example.productsservice.dto.ProductDTO( " +
//                                "       p.id, p.name, " +
//                                "       p.description, p.price, c.nameOfCategory " +
//                                "   ) " +
//                                "from Product p " +
//                                "join p.category c " +
//                               "where p.name like:name or p.category.nameOfCategory  LIKE:category "+
//                                "order by p.price "+descOrAsc)
//                .setParameter("category",keyWord.toLowerCase()+"%")
//                .setParameter("name",keyWord.toLowerCase()+"%")
//                .setFirstResult(firstResult)
//                .setMaxResults(maxResult)
//                .getResultList();
//
//        return productDTOList;
//    }
//
//    public List<Product> paginationProductOrderByPrice(String keyWord ,int firstResult ,int  maxResult  , String descOrAsc ){
//
//        @SuppressWarnings("unchecked")
//        List<Product> productDTOList =  entityManager
//                .createQuery(
//                        "select p "+
//                                "from Product p " +
//                                "join p.category c " +
//                                "where p.name like:name or p.category.nameOfCategory  LIKE:category "+
//                                "order by p.price "+descOrAsc)
//                .setParameter("category",keyWord+"%")
//                .setParameter("name",keyWord+"%")
//                .setFirstResult(firstResult)
//                .setMaxResults(maxResult)
//                .getResultList();
//
//        return productDTOList;
//    }
//
//
////    public List<ProductDTO> paginationProductDTO(int  maxResult , int firstResult ){
////        return entityManager
////                .createQuery(
////                        "select new " +
////                                "   com.example.productsservice.dto.ProductDTO( " +
////                                "       p.id, p.name, " +
////                                "       p.description, p.price, c.nameOfCategory " +
////                                "   ) " +
////                                "from Product p " +
////                                "join p.category c " +
////                                "order by p.id")
////                .setFirstResult(firstResult)
////                .setMaxResults(maxResult)
////                .getResultList();
////    }
//
//    public List<ProductDTO> getPageableProduct(int pageNum , int pageSize  , String keyWord){
//        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
//
//
//        Page<ProductDTO> page = productRepository.findProductByNameOrCategoryOrCompany(pageable,keyWord.toLowerCase()+"%" );
//        List<ProductDTO> products = page.getContent();
//        int totalPage= page.getTotalPages();
//        return products;
//    }
//
//    public List<Product> findAll(){
//        return productRepository.findAll();
//    }
//
//    public boolean existsByName(String name){
//
//        return productRepository.existsByName(name);
//    }
//
//    public boolean existsById(Long id){
//        return productRepository.existsProductById(id);
//    }
//


}
