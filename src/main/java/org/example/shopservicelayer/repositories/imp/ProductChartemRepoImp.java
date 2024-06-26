package org.example.shopservicelayer.repositories.imp;

//@Service
//@Transactional
public class ProductChartemRepoImp {


//    @Query(value = """
//            select  new com.example.productsservice.dto.ProductDTO(
//                p.id as id,
//                p.name as name,
//                p.description as description,
//                p.price as price)
//            from productCharacteristicItem pci
//            join pci.product p
//            join pci.productCharacteristic
//            where pci.productCharacteristic.name LIKE %?1%
//            order by p.price desc """)
//    public Page<ProductDTO> findByProductCharacteristics(Pageable pageable, String characteristics);


//    @PersistenceContext
//    private EntityManager entityManager;
//    @Autowired
//    private ProductSpecItemRepo productCharacteristicItemRepo;
//
//
//    public void save(ProductSpecItem productSpecItem) {
//        entityManager.persist(productSpecItem);
//    }
//
//    public void saveItem(ProductSpecItem productSpecItem){
//        entityManager.persist(productSpecItem);
//    }
//
//
//
//    public List<Product> getProductDTOByCategoryAndProductCharacteristics(String category, String characteristics,
//                                                                          String value){
//        return productCharacteristicItemRepo.getProductByCategoryAndProductCharacteristics(category,characteristics,value);
//    }

}
