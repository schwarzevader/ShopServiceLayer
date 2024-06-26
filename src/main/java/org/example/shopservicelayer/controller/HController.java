package org.example.shopservicelayer.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.example.shopservicelayer.dto.ProductDTO;
import org.example.shopservicelayer.repositories.ProductCategoryRepo;
import org.example.shopservicelayer.repositories.ProductSpecItemRepo;
import org.example.shopservicelayer.service.serviceImp.ProductServiceImp;
import org.example.shopservicelayer.someRequest.ProductPaginationRequest;
import org.example.shopservicelayer.someResponse.CustomResponse;
import org.example.shopservicelayer.someResponse.CustomResponseData;
import org.example.shopservicelayer.someResponse.ProductPaginationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
//@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping(value = "/")
@Slf4j
public class HController {

//    @Autowired
//    private ProductService productService;


    @Autowired
    private ProductServiceImp productServiceImp;
//
    @Autowired
    private ProductSpecItemRepo productSpecItemRepo;
    @Autowired
    private ProductCategoryRepo productCategoryRepo;

//    @RequestMapping(value = "/")
//    public String hello(){
//
////        return "hello "+ SecurityContextHolder.getContext().getAuthentication().getName();
//        return "";
//    }
//
////    @GetMapping(value = "/prod")
////    public ResponseEntity<?> addToCartTest(){
////        ProductList productList = new ProductList();
////        productList.setProductDTOList(productService.searchProdByName("sneakers").stream().toList());
////        return new ResponseEntity<>(productList, HttpStatus.OK);
////    }
//
//    @GetMapping(value = "art")
//    public String getArticle(){
//       // return new ResponseEntity<>("article-------",HttpStatus.OK);
//        return "article-------";
//    }
//
//
//
//
//    @GetMapping(value = "specs")
////    public ResponseEntity<SpecNameAndValueDTO> getSpecs(){
//    public ResponseEntity< Map<String, List<SpecValue>>> getSpecs(){
//        System.out.println("specs");
////        Map<String, List<ProductSpecValueDTO>> specNVDto = productServiceImp.get(2000L);
////        return new ResponseEntity<>(new SpecNameAndValueDTO(specNVDto), HttpStatus.OK);
//        return new ResponseEntity<>(productServiceImp.get(2000L), HttpStatus.OK);
//    }
//
//
//    @GetMapping(value = "spec")
////    public ResponseEntity<SpecNameAndValueDTO> getSpecsList(){
//    public ResponseEntity<List<Specs> > getSpecsList(){
//        return new ResponseEntity<>(productServiceImp.getProdSpecNameList(2000L), HttpStatus.OK);
//    }
//
//
    @GetMapping(value = "s")
    public ResponseEntity<CustomResponse> getS(){
        Map<?, ?> specNVDto = productServiceImp.get(2000L);
//        List<Specs> list = productServiceImp.getProdSpecNameList(2000L);
//        System.out.println("map------------------------------");
//        specNVDto.forEach((key, valueList) -> {
//            System.out.println("Key: " + key);
//            System.out.println("Values: ");
//            valueList.forEach(value -> System.out.println("  " + value.getSpecValue()));
//            System.out.println("--------------------------");
//        });
        return new ResponseEntity<>(new CustomResponse(specNVDto), HttpStatus.OK);
    }



    @GetMapping(value = "specs/{id}")
//    public ResponseEntity<CustomResponse> getSpecs(@PathVariable(value = "id")Long id){
    public ResponseEntity<CustomResponseData> getSpecs(@PathVariable(value = "id")Long id){
//    public ResponseEntity< Map<SpecKey, List<ValueOfSpec>> > getSpecs(@PathVariable(value = "id")Long id){
        Map<?, ?> specNVDtoMap = productServiceImp.getMap(id);
//        Map<String, CustomResponse> specNVDtoMap = new HashMap<>();
//        specNVDtoMap.put("data",new CustomResponse(productServiceImp.getMap(id)));


//        System.out.println("getSpecs ----------------------specs by category id="+id);
////        List<Specs> list = productServiceImp.getProdSpecNameList(id);
////        System.out.println("map------------------------------");
//        System.out.println(specNVDtoMap);
//        System.out.println(" getSpecs ++++++++++++++++++++++++++++");
//        specNVDtoMap.forEach((key, valueList) -> {
//            System.out.println("Key---------: " + key.toString());
//            System.out.println("Values--------: " + valueList.toString());
////            valueList.toString().forEach(value -> System.out.println("  " + value.getSpecValue()));
//            System.out.println("--------------------------");
//        });


//        return new ResponseEntity<>(new CustomResponse(specNVDtoMap), HttpStatus.OK);
        return new ResponseEntity<>(new CustomResponseData(specNVDtoMap), HttpStatus.OK);
//        return new ResponseEntity<>(productServiceImp.getMap(id), HttpStatus.OK);
    }

    @GetMapping(value = "s/{id}")
    public ResponseEntity<CustomResponse> getProductSpecs(@PathVariable(value = "id")Long id){
        Map<?, ?> specNVDto = productServiceImp.get(id);
        Map<?, ?> specNVDtop = productServiceImp.getS(id);

//        System.out.println("----------------------specs by category id="+id);
////        List<Specs> list = productServiceImp.getProdSpecNameList(id);
////        System.out.println("map------------------------------");
//        System.out.println(specNVDtop);
//        System.out.println("++++++++++++++++++++++++++++");
//        specNVDtop.forEach((key, valueList) -> {
//            System.out.println("Key: " + key);
//            System.out.println("Values: " + valueList.toString());
////            valueList.toString().forEach(value -> System.out.println("  " + value.getSpecValue()));
//            System.out.println("--------------------------");
//        });

        return new ResponseEntity<>(new CustomResponse(specNVDto), HttpStatus.OK);
    }
//
////    @GetMapping(value = "p/{id}")
////    @GetMapping(value = "p/{id}")
////    @PostMapping(value = "p/{id}")
//    @PostMapping(value = "p")
////    public ResponseEntity<List<ProductDTO>> getProduct(@PathVariable(value = "id")List<Long> ids){
//    public ResponseEntity<List<ProductDTO>> getProduct(@RequestBody List<Long> ids){
////    public ResponseEntity<Page<ProductDTO>> getProduct(@PathVariable("id") List<Long> ids){
////    public ResponseEntity<List<ProductDTO>> getProduct(){
////    public ResponseEntity<Page<ProductDTO>> getProduct(){
//
////        System.out.println("ids-----------");
////        List<Long> list= Arrays.asList(23000L,2100L);
////        list.forEach(System.out::println);
//
//        List<ProductDTO> productDTOList = productSpecItemRepo.getProductDTOBySpecValueIDS(ids,PageRequest.of(0,100)).getContent();
//        productDTOList.forEach(System.out::println);
//
////        ids.forEach(System.out::println);
////        return new ResponseEntity<>(productSpecItemRepo.getProductDTOBySpecValueIDS(list,PageRequest.of(0,1000)),HttpStatus.OK);
////        return new ResponseEntity<>(productSpecItemRepo.getProductDTOBySpecValueIDS(list,PageRequest.of(0,1000)).getContent(),HttpStatus.OK);
//        return new ResponseEntity<>(productDTOList,HttpStatus.OK);
//    }
//
    @PostMapping(value = "products")
    public ResponseEntity<ProductPaginationResponse> getProductsBySpecValue(@RequestBody ProductPaginationRequest request){
        System.out.println("request ------"+request.toString());
        Page<ProductDTO> page =  productSpecItemRepo.getProductDTOBySpecValueIDSd(request.getSpecsValueId()
//        Page<ProductDTO> page =  productSpecItemRepo.getProductDTOBySpecValueIDS(request.getSpecsValueId(),  request.getSpecsValueId().size()
//        Page<ProductDTO> page =  productSpecItemRepo.getProductDTOBySpecValueIDS(request.getSpecsValueId()
                ,PageRequest.of(request.getSelectedPage()
                        ,request.getPageSize()
                        ,sortBy(request.getSortBy())));
        System.out.println("TOTAL PAGES----------------------------------------"+page.getTotalPages());
        System.out.println("TOTAL elements----------------------------------------"+page.getTotalElements());
//        Page<ProductDTO> page =  productSpecItemRepo.getProductDTOBySpecValueIDS(request.getSpecsValueId()
//                ,PageRequest.of(request.getSelectedPage()
//                        ,request.getPageSize()));
        return new ResponseEntity<>(new ProductPaginationResponse(page.getTotalPages(),page.getTotalElements(),page.getContent()),HttpStatus.OK);
    }

    @PostMapping(value = "c/{id}")
    public ResponseEntity<ProductPaginationResponse> getProductsByCategoryID(@RequestBody ProductPaginationRequest request
            ,@PathVariable(value = "id") Long id){
        System.out.println("request by category  id ------"+id);
        Page<ProductDTO> page =  productCategoryRepo.getProducts(id
                ,PageRequest.of(request.getSelectedPage()
                        ,request.getPageSize()
                        ,sortBy(request.getSortBy())));
        System.out.println("TOTAL ELEMENTS ="+page.getTotalElements()+"----in CATEGORY==="+id);
        page.forEach(System.out::println);
//        Page<ProductDTO> page =  productSpecItemRepo.getProductDTOBySpecValueIDS(request.getSpecsValueId()
//                ,PageRequest.of(request.getSelectedPage()
//                        ,request.getPageSize()));
        return new ResponseEntity<>(new ProductPaginationResponse(page.getTotalPages(),page.getTotalElements(),page.getContent()),HttpStatus.OK);
    }



    private Sort sortBy (String sortBy){
        log.info("sortBy-----------------------");

        switch (sortBy) {
            case "price : low to high" -> {
                System.out.println("asc---------------------------------------------");
//                    return Sort.by("price").ascending();
                return Sort.by(Sort.Order.asc("price"));
            }
            case "price : high to low" -> {
                return Sort.by("price").descending();
            }
            case "rating"->{
                return Sort.by("p.rating").descending();

            }
            default -> {
                System.out.println("sort by id---------------------------");
                return Sort.by("p.shadowRating").descending();
            }
        }
//        if (sortBy.getSortingBy().equals("price : low to high")) {
//            switch (sortBy.getAscOrDesc()) {
//                case "asc" -> {
//                    System.out.println("asc---------------------------------------------");
////                    return Sort.by("price").ascending();
//                    return Sort.by(Sort.Order.asc("price"));
//                }
//                case "desc" -> {
//                    return Sort.by("price").descending();
//                }
//            }
//        }else if (sortBy.getSortingBy().equals("rating")){
//            return Sort.by("rating").descending();
//        }
//        System.out.println("sort by id---------------------------");
//        return Sort.by("p.shadowRating").descending();
//        return Sort.by(Sort.Order.asc("p.id"));
//        return  Sort.by(Sort.Direction.ASC,"p.id");
    }


}
