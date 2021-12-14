package com.chombo.ms.springbootservicioproductos.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.chombo.ms.springbootservicioproductos.models.entity.Producto;
import com.chombo.ms.springbootservicioproductos.models.service.IProductoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductoController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private IProductoService productoService;

    @Value("${server.port}")
    private Integer port;

    @GetMapping("/listar")
    public List<Producto> listar() {
        log.info("Consiming /listar");
        log.info("port: " + port);
        return productoService.findAll().stream().map(m -> {
            m.setPort(port);
            return m;
        }).collect(Collectors.toList());
    }

    @GetMapping("/ver/{id}")
    public Producto detalle(@PathVariable Long id) {
        log.info("Consiming /ver/" + id);
        Producto o = productoService.findById(id);
        o.setPort(port);

        // Timeout por defecto de hystrix es de 1s
        try {
            Thread.sleep(30000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        return o;
    }
}
