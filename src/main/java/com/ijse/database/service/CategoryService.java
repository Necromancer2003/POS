package com.ijse.database.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.database.entity.ItemCategory;

@Service
public interface CategoryService {
    List<ItemCategory>getAllCategory();
    ItemCategory findCategoryById(Long id);
    ItemCategory creatCategory(ItemCategory category);
    ItemCategory updateCategory(Long id, ItemCategory category);
}
