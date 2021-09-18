package com.sample.api;


import com.sample.domain.dto.ProductDto;
import com.sample.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/product")
public class ProductController {

    @Autowired
    private ProductService poductService;

    @PostMapping(path="/add")
    public @ResponseBody String addNewProduct (@RequestBody ProductDto newProduct  ) {

       poductService.addProduct(newProduct);
        return "Saved";
    }
}
