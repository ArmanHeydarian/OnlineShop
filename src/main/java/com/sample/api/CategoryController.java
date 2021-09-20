package com.sample.api;


import com.sample.domain.dto.CategoryDto;
import com.sample.domain.dto.ProductDto;
import com.sample.service.CategoryService;
import com.sample.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping(path = "/add")
    public ResponseEntity<String> addNewCategory(@RequestBody CategoryDto newCategory) {
        try {
           return categoryService.addCategory(newCategory);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong :" + e.getMessage());
        }
    }

    @PostMapping(path = "/get")
    public @ResponseBody
    String getCategoryById(@RequestParam int categoryId) {

        categoryService.getCategoryById(categoryId);
        return "Saved";
    }
}
