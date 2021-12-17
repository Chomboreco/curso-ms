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

    //private Integer port;

    @GetMapping("/listar")
    public List<Producto> listar() {
        log.info("Consiming /listar");
        log.info("port: " + Integer.parseInt(env.getProperty("local.server.port")));

        return productoService.findAll().stream().map(producto -> {
            // m.setPort(port);
            producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
            return producto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/ver/{id}")
    public Producto detalle(@PathVariable Long id) {
        log.info("Consiming /ver/" + id);
        log.info("port: " + Integer.parseInt(env.getProperty("local.server.port")));

        Producto producto = productoService.findById(id);
        producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
        //port = producto.getPort();
        // Timeout por defecto de hystrix es de 1s
        /*try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        
        return producto;
    }
}
