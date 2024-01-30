package com.ijse.database.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.database.entity.ItemCategory;
import com.ijse.database.repository.ItemCategoryRepository;

@Service
public class CategoryServiceImple implements CategoryService{
    @Autowired
    private ItemCategoryRepository categoryRepository;

    @Override
    public List<ItemCategory> getAllCategory(){
        return categoryRepository.findAll();
    }

    @Override 
    public ItemCategory creatCategory(ItemCategory category){
        return categoryRepository.save(category);
    }

    @Override
    public ItemCategory findCategoryById(Long id){
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public ItemCategory updateCategory(Long id,ItemCategory category){
        ItemCategory existingCategory=categoryRepository.findById(id).orElse(null);
        if(existingCategory != null){
            existingCategory.setName(category.getName());
            return categoryRepository.save(existingCategory);
        }else{
            return null;
        }
    }
}
