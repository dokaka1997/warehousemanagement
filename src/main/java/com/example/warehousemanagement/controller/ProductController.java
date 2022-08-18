package com.example.warehousemanagement.controller;

import com.example.warehousemanagement.entity.Product;
import com.example.warehousemanagement.model.request.ExportProductRequest;
import com.example.warehousemanagement.model.response.BestProductSellingResponse;
import com.example.warehousemanagement.model.response.GetAllProductResponse;
import com.example.warehousemanagement.model.response.ProductResponse;
import com.example.warehousemanagement.service.OrderDetailService;
import com.example.warehousemanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("product")
public class ProductController {

    ProductService productService;

    OrderDetailService orderDetailService;

    @Autowired
    public ProductController(ProductService productService, OrderDetailService orderDetailService) {
        this.productService = productService;
        this.orderDetailService = orderDetailService;
    }

    @PostMapping
    public ResponseEntity<Product> addNewProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.addNewProduct(product));
    }

    @GetMapping()
    public ResponseEntity<GetAllProductResponse> getAllProduct(@RequestParam(required = false, defaultValue = "") String name,
                                                               @RequestParam(required = false, defaultValue = "-1") int size,
                                                               @RequestParam(required = false, defaultValue = "-1") Long category,
                                                               @RequestParam int pageIndex, @RequestParam int pageSize) {
        return ResponseEntity.ok(productService.getAllProduct(name, size, category, pageIndex, pageSize));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<Product>> getProductByCategory(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getAllProductByCategory(id));
    }

    @GetMapping("/branch/{id}")
    public ResponseEntity<List<Product>> getProductByBranch(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getAllProductByBranch(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.deleteProductById(id));
    }

    @GetMapping("best_selling")
    public ResponseEntity<List<BestProductSellingResponse>> getTopSelling() {
        return ResponseEntity.ok(orderDetailService.getTop10BestSellingProduct());
    }

    @PostMapping("export")
    public ResponseEntity<ExportProductRequest> exportProduct(@RequestBody ExportProductRequest exportProductRequests) throws Exception {
        return ResponseEntity.ok(productService.addNewExport(exportProductRequests));
    }

    @GetMapping("total")
    public ResponseEntity<Double> getTotalOrder() {
        return ResponseEntity.ok(orderDetailService.getTotalOrder());
    }
    
}
