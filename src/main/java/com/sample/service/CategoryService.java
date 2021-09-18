package com.sample.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.domain.dto.CategoryDto;
import com.sample.domain.model.Category;
import com.sample.domain.model.Product;
import com.sample.domain.repository.CategoryRepository;
import com.sample.domain.repository.PoductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Locale;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ObjectMapper jacksonObjectMapper;

    public String addCategory(CategoryDto categoryDto)
    {
        Category category= jacksonObjectMapper.convertValue(categoryDto, new TypeReference<Category>(){});
        Date date =new Date();
        category.setCreateDate(date);
        categoryRepository.save(category);
        return "Saved";
    }

}
