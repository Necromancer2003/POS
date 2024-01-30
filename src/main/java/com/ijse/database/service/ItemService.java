package com.ijse.database.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.database.dto.ItemDTO;
import com.ijse.database.entity.ItemCategory;
import com.ijse.database.entity.Item;

@Service
public interface ItemService {
    List<Item>getAllItem();
    Item findItemById(Long id);
    Item creatItem(ItemDTO productDTO);
    Item updateItem(Long id, ItemDTO productDTO);
    List<Item> getItemByCategory(Long id);
    Item deleteItem(Long id);
}
