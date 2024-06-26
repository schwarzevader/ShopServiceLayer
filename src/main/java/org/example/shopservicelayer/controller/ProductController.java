package org.example.shopservicelayer.controller;



import lombok.extern.slf4j.Slf4j;

import org.example.shopservicelayer.service.serviceImp.ProductServiceImp;
import org.example.shopservicelayer.someResponse.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//@Controller
@RestController
@RequestMapping("products")
@Slf4j
public class ProductController {

    @Autowired
    private ProductServiceImp productServiceImp;

    @GetMapping("/info/{id}")
    public ResponseEntity <CustomResponse> getProductInfo(@PathVariable(value = "id") Long id){
      log.info("---------------getProductInfo for id = "+id);

        return new ResponseEntity<>(new CustomResponse(productServiceImp.getProductSpecsById(id)), HttpStatus.OK);
    }

//    @Autowired
//    private ProductService productService;
//
//    @Autowired
//    private ProductRepositoryImp productRepositoryImp;
//
//    @GetMapping("/page/{pageNum}")
//    public String viewPage(Model model,
//                           @PathVariable(name = "pageNum") int pageNum) {
//
//        Page<Product> page = productService.listAll(pageNum);
//        List<Product> listProducts = page.getContent();
//
//        log.info("totalPages=" + page.getTotalPages());
//        log.info("totalElements=" + page.getTotalElements());
//        log.info("product list ---" + listProducts.toString());
//
//        model.addAttribute("currentPage", pageNum);
//        model.addAttribute("totalPages", page.getTotalPages());
//        model.addAttribute("totalItems", page.getTotalElements());
//        model.addAttribute("listProducts", listProducts);
//
//        return "products/page";
//    }
//
////    @GetMapping("/new")
////    public String newProd(@ModelAttribute("product") Product product){
////
////        //productService.saveProduct(product);
////        log.info("---getmaping--- product");
////        return "products/new";
////    }
//
//    @GetMapping("/new")
//    public ModelAndView newProd(@ModelAttribute("product") Product product) {
//        //public ModelAndView newProd(){
//        //public ModelAndView newProd(@RequestBody Product product){
//
//        //productService.saveProduct(product);
//        log.info("---getmaping--- product");
//
//        return new ModelAndView("products/new");
//    }
//
//    @GetMapping("/filter")
//    private ResponseEntity<List<ProductDTO>> getFilterLIst() {
//        return ResponseEntity.ok(productRepositoryImp.searchAndPaginationProductDTOOrderByPrice("Sneakers", 0, 10, "desc"));
//    }
//
//
////
////    @GetMapping("/filter")
////    private ResponseEntity<String> getFilterLIst(){
////        return new ResponseEntity<String>("data ok",HttpStatus.OK);
////    }
//
//    //
//    @PostMapping("/new")
//    public String newProduct(@ModelAttribute("product") Product product) {
//
//        productService.saveProduct(product);
//        //return "/products/new";
//        log.info("-----postmaping product-----");
//        //return"redirect:products/h";
//        //return"products/h";
//        //return"redirect:http://localhost:8073/shop/products/new";
//        return "redirect:http://localhost:8073/products/h";
//        // return"redirect:/products/h";
////        ModelAndView mv = new ModelAndView();
////        mv.setViewName("someStr");
////        mv.getModel().put("data", "Welcome home man");
//
//        //return mv;
////RedirectView redirectView = new RedirectView();
////       redirectView.setUrl("http://localhost:8073/shop/home/str");
////
////       return redirectView;
//    }
////-----
////    @PostMapping("/new")
////    //public ResponseEntity  saveOrganization(@RequestBody Product product) {
////    public ResponseEntity<Void>  saveOrganization(@ModelAttribute("product") Product product) {
////        log.info("---post--- product info = "+product.toString());
////        productService.saveProduct(product);
////        return ResponseEntity.status(HttpStatus.OK).build();//.location(URI.create("http://localhost:8073/shop/home/str")).build();
////    }
//
//    @GetMapping("/h")
//    public ResponseEntity<String> getH() {
//        return new ResponseEntity<>("h", HttpStatus.OK);
//    }
}
