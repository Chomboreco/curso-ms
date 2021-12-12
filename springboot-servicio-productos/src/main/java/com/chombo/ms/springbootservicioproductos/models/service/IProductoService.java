package com.chombo.ms.springbootservicioproductos.models.service;

import java.util.List;

import com.chombo.ms.springbootservicioproductos.models.entity.Producto;

public interface IProductoService {
    
    List<Producto> findAll();
    Producto findById(Long id);
}
