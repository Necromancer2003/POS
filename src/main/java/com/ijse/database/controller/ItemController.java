package com.ijse.database.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.database.dto.ItemDTO;
import com.ijse.database.entity.Item;
import com.ijse.database.service.ItemService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = "*")//x orgin "*"all alowed 
public class ItemController {
    
    @Autowired
    private ItemService itemService;

    @GetMapping("/products")
    public ResponseEntity<List<Item>> getAllItem(){
        return ResponseEntity.status(HttpStatus.OK).body(itemService.getAllItem());
    }

    @PostMapping("/products")
    public ResponseEntity<?> createItem(@RequestBody ItemDTO itemDTO){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(itemService.creatItem(itemDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Faild to creat product");
        }
        
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id){
        Item item = itemService.findItemById(id);

        if(item !=null){
            return ResponseEntity.status(HttpStatus.OK).body(item);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    @PutMapping("/products/{id}")
    public Item updateItem(@PathVariable Long id,@RequestBody ItemDTO itemDTO){
        return itemService.updateItem(id,itemDTO);
    }

    @GetMapping("/categories/{id}/products")
    public ResponseEntity<List<Item>> getItemByCategory(@PathVariable Long id ) {
        return ResponseEntity.ok().body(itemService.getItemByCategory(id));
    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
        Item item = itemService.deleteItem(id);

        if (item != null) {
            return ResponseEntity.status(HttpStatus.OK).body("Item deleted !");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
        }
    }
    
}
