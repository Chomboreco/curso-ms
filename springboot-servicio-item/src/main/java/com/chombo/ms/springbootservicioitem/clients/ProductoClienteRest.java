package com.chombo.ms.springbootservicioitem.clients;

import java.util.List;

import com.chombo.ms.springbootservicioitem.model.Producto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "servicio-productos")
public interface ProductoClienteRest {
    
    @GetMapping(path = "/listar")
    public List<Producto> listar();

    @GetMapping("/ver/{id}")
    public Producto detalle(@PathVariable Long id);
}
