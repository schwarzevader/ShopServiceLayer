package org.example.shopservicelayer.repositories.imp;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.example.shopservicelayer.entity.Product;
import org.example.shopservicelayer.entity.ProductSpecsValue;
import org.example.shopservicelayer.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
//@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
public class ProductRepositoryImp {

    @PersistenceContext
    private EntityManager entityManager;





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
    public void saveProduct(Product product){
//        productRepository.save(product);
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(product);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }

    }

    public void updateProduct(Long id, Product product , List<ProductSpecsValue> removeSpecValue, List<ProductSpecsValue>addSpecValue){
//        productRepository.save(product);

        productRepository.findProductById(product.getId());
        try {
            entityManager.getTransaction().begin();
//            Product prod =  entityManager.find(Product.class, id);
            Product prod =  entityManager.createQuery("select p " +
                                                               "from products p " +
                                                               "join  fetch p.productSpecItemList iti" +
                                                               " where p.id = :id ",Product.class).setParameter("id",id).getSingleResult();
//            Product prod =  new Product();
            prod.setId(id);
            prod.setName(product.getName());
            prod.setDescription(product.getDescription());
            prod.setShadowRating(product.getShadowRating());
            prod.setRating(product.getRating());
            prod.setPrice(product.getPrice());
            prod.setQuantity(product.getQuantity());
            prod.setProductCategory(product.getProductCategory());
            removeSpecValue.forEach(prod::removeSpecValueS);
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
        }catch (Exception e){
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
    public Product findById(Long id){
//      return productRepository.findProductByIdIs(id);
      return entityManager.find(Product.class,id);
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
