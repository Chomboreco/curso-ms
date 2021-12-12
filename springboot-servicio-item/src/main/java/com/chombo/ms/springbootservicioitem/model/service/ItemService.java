package com.chombo.ms.springbootservicioitem.model.service;

import java.util.List;

import com.chombo.ms.springbootservicioitem.model.Item;

public interface ItemService {

    public List<Item> findAll();

    public Item findById(Long id, Integer cantidad);
}
