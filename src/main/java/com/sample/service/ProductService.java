package com.sample.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.domain.dto.ProductDto;
import com.sample.domain.model.Product;
import com.sample.domain.repository.PoductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class ProductService {
    @Autowired
    private PoductRepository poductRepository;

    @Autowired
    private ObjectMapper jacksonObjectMapper;

    public String addProduct(ProductDto productDto)
    {
        Product product= jacksonObjectMapper.convertValue(productDto, new TypeReference<Product>(){});
        Date date =new Date();
        product.setCreateDate(date);
        poductRepository.save(product);
        return "Saved";
    }

}
