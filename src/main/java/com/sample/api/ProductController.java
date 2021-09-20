package com.sample.api;


import com.mysql.cj.x.protobuf.Mysqlx;
import com.sample.domain.dto.ProductCommentDto;
import com.sample.domain.dto.ProductDto;
import com.sample.domain.dto.ProductRateDto;
import com.sample.domain.model.Product;
import com.sample.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/product")
public class ProductController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ProductService poductService;
    //--------------------------------------------------------------------
    @PostMapping(value = "/add")
    public @ResponseBody  ResponseEntity<String> addNewProduct(@RequestBody ProductDto newProduct) {
        try {
            return poductService.addProduct(newProduct);

        } catch (Exception e) {

            return ResponseEntity.badRequest().body("Something went wrong :" + e.getMessage());
        }
    }
    //-----------------------------------------------------------------------------------
    @GetMapping(value = "/getall")
    public ResponseEntity<List<Product>> getAllProduct() {
        try {
            List list  = poductService.getAllProducts();
            return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    //---------------------------------
    @GetMapping(value = "/searchproduct")
    public ResponseEntity<List<Product>>
    getByProductTitleAndPrice(@RequestParam(required = false) String title, @RequestParam(required = false) Long price, @RequestParam(required = false) Short rate) {
        try {
            List list = poductService.findProductsByParams(title, price ,rate);
            return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
        }
        catch (Exception e) {

            return ResponseEntity.badRequest().body(null);
        }
    }
    //--------------------------------------------------------------------------------------
    @PostMapping(value = "/putComment")
    public @ResponseBody ResponseEntity<String> addNewProductComment(@RequestBody ProductCommentDto productCommentDto) {
        Authentication authentication;
        // Get User Name for finding User Entity by name
        try {
            authentication = SecurityContextHolder.getContext().getAuthentication();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("User is not authenticate");
        }

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            // Add new Comment to Repository and then Database
            try {
                return poductService.addProductComment(productCommentDto, authentication.getName());
            }
            catch (Exception e)
            {
                return ResponseEntity.badRequest().body("Something went wrong" + e.getMessage());
            }
        }

        return ResponseEntity.badRequest().body("User is not authenticated");
    }
    //--------------------------------------------------------------------------------------
    @PostMapping(value = "/putRate")
    public @ResponseBody ResponseEntity<String> addNewProductRate(@RequestBody ProductRateDto productRateDto) {
        Authentication authentication;
        // Get User Name for finding User Entity by name
        try {
            authentication = SecurityContextHolder.getContext().getAuthentication();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("User is not authenticate");
        }

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            // Add new Rate to Repository and then Database
            try {
                return poductService.addProductRate(productRateDto, authentication.getName());
            }
            catch (Exception e)
            {
                return ResponseEntity.badRequest().body("Something went wrong: " + e.getMessage());
            }
        }

        return ResponseEntity.badRequest().body("User is not authenticated");
    }
}
