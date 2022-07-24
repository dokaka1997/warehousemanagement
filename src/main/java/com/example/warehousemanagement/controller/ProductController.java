package com.example.warehousemanagement.controller;

import com.example.warehousemanagement.entity.Product;
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

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> addNewProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.addNewProduct(product));
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProduct(@RequestParam int pageIndex, @RequestParam int pageSize) {
        return ResponseEntity.ok(productService.getAllProduct(pageIndex, pageSize));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
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
}
