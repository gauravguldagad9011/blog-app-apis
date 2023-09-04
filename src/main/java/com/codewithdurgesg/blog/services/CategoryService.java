package com.codewithdurgesg.blog.services;

import com.codewithdurgesg.blog.payloads.CategoryDTO;
import com.codewithdurgesg.blog.payloads.UserDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getAllCategories(CategoryDTO c);
    CategoryDTO getCategoryById(CategoryDTO c,int id);
    CategoryDTO createCategory(CategoryDTO c);
    CategoryDTO updateCategory(CategoryDTO c,int id);
    void deleteCategoryById(CategoryDTO c,int id);
}
