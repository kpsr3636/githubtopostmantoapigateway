package com.examplez.demo.service;

import com.examplez.demo.entity.Item;
import com.examplez.demo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public Item updateItem(Long id, Item itemDetails) {
        Item item = itemRepository.findById(id).orElse(null);
        if (item != null) {
            item.setName(itemDetails.getName());
            return itemRepository.save(item);
        }
        return null;
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
}


