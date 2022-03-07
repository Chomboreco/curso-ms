package com.chombo.ms.springbootservicioproductos.models.repository;

import com.chombo.ms.springbootserviciocommons.models.entity.Producto;

import org.springframework.data.repository.CrudRepository;

public interface ProductoRepository extends CrudRepository<Producto, Long> {

}
