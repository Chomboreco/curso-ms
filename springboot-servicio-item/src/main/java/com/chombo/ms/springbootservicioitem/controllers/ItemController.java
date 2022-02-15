package com.chombo.ms.springbootservicioitem.controllers;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.chombo.ms.springbootservicioitem.model.Item;
import com.chombo.ms.springbootservicioitem.model.Producto;
import com.chombo.ms.springbootservicioitem.model.service.ItemService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@RestController
public class ItemController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private CircuitBreakerFactory cbFactory;

    @Autowired
    @Qualifier("itemServiceFeign")
    private ItemService itemService;

    @GetMapping("/listar")
    public List<Item> listar(@RequestParam(name = "nombre", required = false) String nombre, @RequestHeader(name = "token-request", required = false) String token) {
        log.info("nombre: " + nombre);
        log.info("token: " + token);
        return itemService.findAll();
    }

    //@HystrixCommand(fallbackMethod="metodoAlternativo")
    @GetMapping("/ver/{id}/cantidad/{cantidad}")
    public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad) {
        return cbFactory.create("items").run(() -> itemService.findById(id, cantidad), e -> metodoAlternativo(id, cantidad, e));
    }

    @CircuitBreaker(name = "items", fallbackMethod = "metodoAlternativo")
    @GetMapping("/ver2/{id}/cantidad/{cantidad}")
    public Item detalle2(@PathVariable Long id, @PathVariable Integer cantidad) {
        return itemService.findById(id, cantidad);
    }

    @CircuitBreaker(name = "items", fallbackMethod = "metodoAlternativo2")
    @TimeLimiter(name = "items")
    @GetMapping("/ver3/{id}/cantidad/{cantidad}")
    public CompletableFuture<Item> detalle3(@PathVariable Long id, @PathVariable Integer cantidad) {
        return CompletableFuture.supplyAsync(() -> itemService.findById(id, cantidad));
    }

    public Item metodoAlternativo(Long id, Integer cantidad, Throwable e) {
        log.error("Error", e);
        Item item = new Item();
        Producto producto = new Producto();
        item.setCantidad(cantidad);
        producto.setId(id);
        producto.setNombre("Camara Nikon");
        producto.setPrecio(9500.00);
        item.setProducto(producto);
        return item;
    }

    public CompletableFuture<Item> metodoAlternativo2(Long id, Integer cantidad, Throwable e) {
        log.error("Error", e);
        Item item = new Item();
        Producto producto = new Producto();
        item.setCantidad(cantidad);
        producto.setId(id);
        producto.setNombre("Camara Nikon");
        producto.setPrecio(9500.00);
        item.setProducto(producto);
        return CompletableFuture.supplyAsync(() -> item);
    }
}
