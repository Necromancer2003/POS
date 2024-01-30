package com.ijse.database.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ijse.database.dto.ItemDTO;
import com.ijse.database.entity.ItemCategory;
import com.ijse.database.entity.Order;
import com.ijse.database.entity.Item;
import com.ijse.database.repository.ItemCategoryRepository;
import com.ijse.database.repository.ItemRepository;
import com.ijse.database.repository.OrderRepository;

@Service
public class ItemServiceImple implements ItemService{
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemCategoryRepository categoryRepository;

    @Override
    public List<Item> getAllItem(){
        return itemRepository.findAll();
    }

    @Override 
    public Item creatItem(ItemDTO itemDTO){
        ItemCategory category = categoryRepository.findById(itemDTO.getCategory_id()).orElse(null);

        if (category !=null) {
            Item item =new Item();

            item.setName(itemDTO.getName());
            item.setPrice(itemDTO.getPrice());
            item.setQty(itemDTO.getQty());
            item.setCategory(category);
            
            return itemRepository.save(item);
        }else{
            return null;
        }    
    }

    @Override
    public Item findItemById(Long id){
        return itemRepository.findById(id).orElse(null);
    }

    @Override
    public Item updateItem(Long id,ItemDTO itemDTO){
        Item existingitem=itemRepository.findById(id).orElse(null);

        ItemCategory category = categoryRepository.findById(itemDTO.getCategory_id()).orElse(null);


        if(existingitem != null){
            existingitem.setName(itemDTO.getName());
            existingitem.setCategory(category);
            existingitem.setQty(itemDTO.getQty());
            existingitem.setPrice(itemDTO.getPrice());
            
            return itemRepository.save(existingitem);
        }else{
            return null;
        }
    }

    @Override
    public List<Item> getItemByCategory(Long id){
        ItemCategory category = categoryRepository.findById(id).orElse(null);

        if(category != null){
            return itemRepository.findItemByCategory(category);
        }else{
            return null;
        }
    }

    @Override
    public Item deleteItem(Long id){
        Item existingItem = itemRepository.findById(id).orElse(null);

        if (existingItem != null) {
            itemRepository.delete(existingItem);;
            return existingItem;
        } else {
            return null;
        }
    }
}
