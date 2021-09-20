package com.sample.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.domain.dto.ProductCommentDto;
import com.sample.domain.dto.ProductDto;
import com.sample.domain.dto.ProductRateDto;
import com.sample.domain.model.*;
import com.sample.domain.repository.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.criteria.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private PoductCommentRepository poductCommentRepository;
    @Autowired
    private PoductRateRepository poductRateRepository;
    @Autowired
    private PoductRepository poductRepository;


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ObjectMapper jacksonObjectMapper;
    //------------------------------------------------------------------------------------
    public ResponseEntity<String> addProduct(ProductDto productDto) throws Exception
    {
        Product product= jacksonObjectMapper.convertValue(productDto, new TypeReference<Product>(){});
        Date date =new Date();
        product.setCreateDate(date);

            Optional<Category> category = categoryRepository.findById(productDto.getCategoryId());
            if (category != null)
                product.setCategory(category.get());
            else ResponseEntity.badRequest().body("Category Id was not found");

        poductRepository.save(product);
        return ResponseEntity.ok("Product Saved Successfully");
    }
    //------------------------------------------------------------------------------------
    public ResponseEntity<String> addProductComment(ProductCommentDto productCommentDto, String userName) throws Exception {

        //Map Eatery DTO to ProductComment Model 
        ProductComment productComment = jacksonObjectMapper.convertValue(productCommentDto, new TypeReference<ProductComment>() {});
        Date date = new Date();
        productComment.setCreateDate(date);

        //Find Product by Id and Add it to Comment Model
        Optional<Product> product = poductRepository.findById(productCommentDto.getProductId());
        if (product != null)
            productComment.setProduct(product.get());
        else
            return ResponseEntity.badRequest().body("Product Id was not found");

        //Find User by Id and Add it to Comment Model
        UserModel user = userRepository.findByUsername(userName);
        if (user != null)
            productComment.setUser(user);
        else
            return ResponseEntity.badRequest().body("User Id was not found");

        // Add requested productComment to poductCommentRepository
        poductCommentRepository.save(productComment);

        return ResponseEntity.ok("Comment Saved Successfully");
    }
    //------------------------------------------------------------------------------------
    public ResponseEntity<String> addProductRate(ProductRateDto productRateDto, String userName) throws Exception {

        //Map Eatery DTO to ProductRate Model
        ProductRate productRate = jacksonObjectMapper.convertValue(productRateDto, new TypeReference<ProductRate>() {});
        Date date = new Date();
        productRate.setCreateDate(date);

        //Find Product by Id and Add it to Rate Model
        Optional<Product> product = poductRepository.findById(productRateDto.getProductId());
        if (product != null)
            productRate.setProduct(product.get());
        else
            return ResponseEntity.badRequest().body("Product Id was not found");

        //Find User by Id and Add it to Rate Model
        UserModel user = userRepository.findByUsername(userName);
        if (user != null)
            productRate.setUser(user);
        else
            return ResponseEntity.badRequest().body("User Id was not found");

        // Add requested productRate to poductRateRepository
        poductRateRepository.save(productRate);

        return ResponseEntity.ok("Rate Saved Successfully");
    }
    //------------------------------------------------------------------------------------
    public List<Product> getAllProducts() throws Exception {
        return poductRepository.GetAllRows();
    }
    //------------------------------------------------------------------------------------

    @PersistenceContext
    EntityManager entityManager;
    public List<Product> findProductsByParams(String title, Long price , Short rate) throws Exception {

        //Search multi properties on Product using CriteriaQuery
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> root = cq.from(Product.class);
        Fetch<Product, Category> productsCategory = root.fetch("category", JoinType.LEFT);
        Join<Product, Category> products = (Join<Product, Category>) productsCategory;
        // Design Predicts for query
        List<Predicate> predicates = new ArrayList<>();
        if (title !=null && !title.equals("")) {
            predicates.add(cb.like(root.get("title"), "%" + title + "%"));
        }
        if (price!= null) {
            predicates.add(cb.equal(root.get("price"), price));
        }
        if (rate!= null) {
            predicates.add(cb.equal(root.get("avgRate"), rate));
        }

        cq.where(predicates.toArray(new Predicate[predicates.size()]));
        return entityManager.createQuery(cq).getResultList();
    }
    //------------------------------------------------------------------------------------

    public ResponseEntity<String> deleteProduct(int productId) throws Exception {
        poductRepository.deleteById(productId);
        return ResponseEntity.ok("Product Has Been Deleted Successfully");

    }

}
