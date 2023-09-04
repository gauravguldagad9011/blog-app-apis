package com.codewithdurgesg.blog.controllers;

import com.codewithdurgesg.blog.payloads.ApiResponse;
import com.codewithdurgesg.blog.payloads.CategoryDTO;
import com.codewithdurgesg.blog.services.CategoryService;
//import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO c){
        CategoryDTO created=this.categoryService.createCategory(c);

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@Valid @RequestBody CategoryDTO c,@PathVariable int id) {
        CategoryDTO c1 = this.categoryService.getCategoryById(c, id);

        return new ResponseEntity<>(c1, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> getAllCustomer(@Valid @RequestBody CategoryDTO c){
        List<CategoryDTO> list=this.categoryService.getAllCategories(c);

        return new ResponseEntity<List<CategoryDTO>>(list,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO c1,@PathVariable int id){
        CategoryDTO c=this.categoryService.updateCategory(c1,id);

        return new ResponseEntity<>(c,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategoryById(@Valid @RequestBody CategoryDTO c,@PathVariable int id){
        this.categoryService.deleteCategoryById(c,id);

        return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted Successfully",true),HttpStatus.OK);
    }
}
