package com.sample.api;


import com.sample.domain.dto.CategoryDto;
import com.sample.domain.dto.ProductDto;
import com.sample.service.CategoryService;
import com.sample.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping(path="/add")
    public @ResponseBody String addNewCategory (@RequestBody CategoryDto newCategory  ) {

        categoryService.addCategory(newCategory);
        return "Saved";
    }
}
