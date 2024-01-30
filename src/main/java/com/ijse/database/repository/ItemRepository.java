package com.ijse.database.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ijse.database.entity.ItemCategory;
import com.ijse.database.dto.ItemDTO;
import com.ijse.database.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item , Long> {

    @Query ("SELECT p FROM Item p WHERE p.category = :category")
    List<Item> findItemByCategory(@Param("category") ItemCategory category);

}
