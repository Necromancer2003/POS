package com.ijse.database.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.database.dto.OrderDTO;
import com.ijse.database.entity.Order;
import com.ijse.database.entity.Item;
import com.ijse.database.repository.OrderRepository;
import com.ijse.database.repository.ItemRepository;


@Service
public class OrderServiceImple implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    @Override
    public Order createOrder(OrderDTO orderDTO){
        Order order =new Order();

        List<Long> item=orderDTO.getItem();
        Set<Item> itemSet =new HashSet<>();

        order.setTotal(0.0);

        for (Long itemId : item) {
            Item product =itemRepository.findById(itemId).orElse(null);

            if(product !=null && product.getQty() != 0){
                itemSet.add(product);
                order.setTotal(order.getTotal()+product.getPrice());

                //stock - karanna puluwan methanama
            }
        }
        Double tax =(order.getTotal()/100)*15;
        order.setTax(tax);
        order.setOrdertime(LocalDateTime.now());
        order.setItem(itemSet);
    
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long id){
        return orderRepository.findById(id).orElse(null);
    }

    

    public void minItemStock(Set<Item> items){  
       
        for (Item item : items) {
            Min stk =new Min();
            if(item.getId()==stk.saveid){
                stk.min+=1;
            }else{
                stk.min=1;
            }
            stk.saveid=item.getId();
            item.setQty(item.getQty()- stk.min);
            itemRepository.save(item);
        }
    }
}

