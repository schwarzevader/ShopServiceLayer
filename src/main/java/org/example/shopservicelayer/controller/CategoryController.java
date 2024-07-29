package org.example.shopservicelayer.controller;



import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.example.shopservicelayer.dto.CategoriesAndSpecsDto;
import org.example.shopservicelayer.dto.CategoryDTO;
import org.example.shopservicelayer.repositories.jpaInterfaces.ProductCategoryRepo;
import org.example.shopservicelayer.service.serviceInterfaces.CategoriesAndSpecsService;
import org.example.shopservicelayer.someResponse.CategoriesResponse;
import org.example.shopservicelayer.someResponse.CategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping(value = "category")
//@AllArgsConstructor
public class CategoryController {


    private CategoriesAndSpecsService categoriesAndSpecsService;

//    @Autowired
    private ProductCategoryRepo productCategoryRepo;

    @Autowired
    public CategoryController(CategoriesAndSpecsService categoriesAndSpecsService, ProductCategoryRepo productCategoryRepo) {
        this.categoriesAndSpecsService = categoriesAndSpecsService;
        this.productCategoryRepo = productCategoryRepo;
    }

    //some  janegyy
//some  janegyy
//some  janegyy
//some  janegyy

    @GetMapping
//    public ResponseEntity<CustomResponse>getAllCategories(){
    public ResponseEntity<CategoryResponse>getAllCategories(){
//        return new ResponseEntity<>(productCategoryRepo.getAllCategoryDTO(), HttpStatus.OK);
        System.out.println("getAllCategories-------------------------------");
        List<CategoryDTO> categoryDTOList= productCategoryRepo.getAllCategoryDTO();
        categoryDTOList.forEach(System.out::println);
//        Map<String, List<CategoryDTO>> someMap = new HashMap<>();
//        someMap.put("categories", categoryDTOList);
//        return new ResponseEntity<>(new CustomResponse(Map.of("categories",categoryDTOList)), HttpStatus.OK);
        return new ResponseEntity<>(new CategoryResponse(categoryDTOList), HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<CategoriesResponse>getAllCategoriesAndSpecs(){
        System.out.println("getAllCategoriesAndSpecs-------------------------------");
        List<CategoriesAndSpecsDto> categoriesAndSpecsDtos= categoriesAndSpecsService.getAllCategories(true);
        categoriesAndSpecsDtos.forEach(System.out::println);
        return new ResponseEntity<>(
                new CategoriesResponse(categoriesAndSpecsDtos),
                HttpStatus.OK
        );
    }

}
