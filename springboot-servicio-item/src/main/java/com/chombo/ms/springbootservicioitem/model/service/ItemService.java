package com.chombo.ms.springbootservicioitem.model.service;

import java.util.List;

import com.chombo.ms.springbootserviciocommons.models.entity.Producto;
import com.chombo.ms.springbootservicioitem.model.Item;

public interface ItemService {

    public List<Item> findAll();

    public Item findById(Long id, Integer cantidad);

    public Producto save(Producto producto);

    public Producto update(Producto producto, Long id);

    public void delete(Long id);
}
