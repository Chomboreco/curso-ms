package com.chombo.ms.springbootservicioproductos.controllers;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.chombo.ms.springbootserviciocommons.models.entity.Producto;
import com.chombo.ms.springbootservicioproductos.models.service.IProductoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductoController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private IProductoService productoService;

    @Autowired
    private Environment env;

    // private Integer port;

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
    public Producto detalle(@PathVariable Long id) throws InterruptedException {
        log.info("Consiming /ver/" + id);
        log.info("port: " + Integer.parseInt(env.getProperty("local.server.port")));

        // Simulamos el error para probar
        if (id.equals(10L)) {
            throw new IllegalStateException("Producto no encontrado");
        }

        if (id.equals(7L)) {
            TimeUnit.SECONDS.sleep(5L);
        }

        Producto producto = productoService.findById(id);
        producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
        // port = producto.getPort();

        return producto;
    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public Producto crear(@RequestBody Producto producto) {
        return productoService.save(producto);
    }

    @PutMapping("/editar/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Producto editar(@RequestBody Producto producto, @PathVariable Long id) {
        Producto p = productoService.findById(id);
        p.setNombre(producto.getNombre());
        p.setPrecio(producto.getPrecio());

        return productoService.save(p);
    }

    @DeleteMapping("/eliminar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        productoService.deleteById(id);
    }
}
