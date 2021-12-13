package com.chombo.ms.springbootservicioproductos.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.chombo.ms.springbootservicioproductos.models.entity.Producto;
import com.chombo.ms.springbootservicioproductos.models.service.IProductoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductoController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private IProductoService productoService;

    @Autowired
    private Environment env;

    @GetMapping("/listar")
    public List<Producto> listar() {
        log.info("Consiming /listar");
        return productoService.findAll().stream().map(m -> {
            m.setPort(Integer.parseInt(env.getProperty("server.port")));
            return m;
        }).collect(Collectors.toList());
    }

    @GetMapping("/ver/{id}")
    public Producto detalle(@PathVariable Long id) {
        log.info("Consiming /ver/" + id);
        Producto o = productoService.findById(id);
        o.setPort(Integer.parseInt(env.getProperty("server.port")));
        return o;
    }
}
