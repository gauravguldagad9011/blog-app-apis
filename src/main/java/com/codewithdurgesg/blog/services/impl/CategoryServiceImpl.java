package com.codewithdurgesg.blog.services.impl;

import com.codewithdurgesg.blog.entities.Category;
import com.codewithdurgesg.blog.exceptions.ResourceNotFoundException;
import com.codewithdurgesg.blog.payloads.CategoryDTO;
import com.codewithdurgesg.blog.repositories.CategoryRepo;
import com.codewithdurgesg.blog.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("CategoryServiceImpl")
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CategoryRepo categoryRepo;
    @Override
    public List<CategoryDTO> getAllCategories(CategoryDTO c) {
        List<Category> list=this.categoryRepo.findAll();
        List<CategoryDTO> list1 = list.stream().map(e-> categoryToDto(e)).collect(Collectors.toList());
        return list1;
    }

    @Override
    public CategoryDTO getCategoryById(CategoryDTO c, int id) {
        Category c1 = this.categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Category"," Id ",id));

        return categoryToDto(c1);
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO c) {
        Category c1=this.DtoToCategory(c);
        Category created=this.categoryRepo.save(c1);

        return categoryToDto(created);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO c,int id) {
        Category c1=this.categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Category"," Id ",id));
        c1.setTittle(c.getTittle());
        c1.setDescription(c.getDescription());

        Category updatedCategory=this.categoryRepo.save(c1);

        return categoryToDto(updatedCategory);
    }

    @Override
    public void deleteCategoryById(CategoryDTO c, int id) {
    Category c1=this.categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Category"," Id ",id));
    this.categoryRepo.delete(c1);
    }

    public Category DtoToCategory(CategoryDTO c){
        return this.modelMapper.map(c,Category.class);
    }
    public CategoryDTO categoryToDto(Category c){
        return this.modelMapper.map(c,CategoryDTO.class);
    }
}
