package org.example.shopservicelayer.repositories.imp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.shopservicelayer.dto.ProductUpdateDto;
import org.example.shopservicelayer.entity.Product;
import org.example.shopservicelayer.repositories.repositoriesInterfaces.ProductCrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
@AllArgsConstructor
public class ProductCrudRepositoryImp implements ProductCrudRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Product save(Product product) {
        return this.entityManager.merge(product);
    }


    @Override
    public Product findProductById(Long id) {
        return this.entityManager.find(Product.class, id);
    }

    @Override
    public Product update(ProductUpdateDto productUpdateDto) {
        Long id = productUpdateDto.getProduct().getId();

        entityManager.getTransaction().begin();
//            Product prod =  entityManager.find(Product.class, id);
        Product prod = entityManager.createQuery("select p " +
                "from products p " +
                "join  fetch p.productSpecItemList iti" +
                " where p.id = :id ", Product.class).setParameter("id", id).getSingleResult();
//            Product prod =  new Product();
        prod.setId(id);
        prod.setName(productUpdateDto.getProduct().getName());
        prod.setDescription(productUpdateDto.getProduct().getDescription());
        prod.setShadowRating(productUpdateDto.getProduct().getShadowRating());
        prod.setRating(productUpdateDto.getProduct().getRating());
        prod.setPrice(productUpdateDto.getProduct().getPrice());
        prod.setQuantity(productUpdateDto.getProduct().getQuantity());
        prod.setProductCategory(productUpdateDto.getProduct().getProductCategory());
        productUpdateDto.getRemoveSpecValue().forEach(prod::removeSpecValue);
        productUpdateDto.getAddSpecValue().forEach(prod::addProductSpecsValue);
        return entityManager.merge(prod);
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


    }

    @Override
    public void deleteProductById(Long id) {
        this.entityManager.remove(this.findProductById(id));
    }
}
