package com.example.warehousemanagement.controller;

import com.example.warehousemanagement.entity.Category;
import com.example.warehousemanagement.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("category")
public class CategoryController {

    CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public ResponseEntity<List<Category>> getIncomeBranchById() {
        return ResponseEntity.ok(categoryService.getAllCategory());
    }

}
