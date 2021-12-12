package com.chombo.ms.springbootservicioproductos.models.repository;

import com.chombo.ms.springbootservicioproductos.models.entity.Producto;

import org.springframework.data.repository.CrudRepository;

public interface ProductoRepository extends CrudRepository<Producto, Long> {

}
