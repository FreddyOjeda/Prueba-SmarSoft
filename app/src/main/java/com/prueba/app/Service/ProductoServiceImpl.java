package com.prueba.app.Service;

import com.prueba.app.Entity.Cliente;
import com.prueba.app.Entity.Producto;
import com.prueba.app.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

/*
Como esta ya es el servico logico se utiliza la etiqueta  @Service para indicar que es un servicio
como tal como esta es la implentaciond de un intarfaz todos los metodos que hay en esta clase estan encapsulados
porque ya estan definidos en la interfaz implentada
 */
@Service
public class ProductoServiceImpl implements  ProductoService{

    //se hace la instancia por inyeccion del repositorio de producto
    @Autowired
    ProductoRepository productoRepository;

    @Override
    @Transactional(readOnly = true)
    public ArrayList<Producto> findAll() {
        return (ArrayList<Producto>) productoRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Producto> findById(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    @Transactional
    public Producto create(Producto producto) {
        return productoRepository.save(producto);
    }

    /*
    en este metodo se recibe el producto con la informacion actualizada y su id,
    el codigo se encarga de buscar y validar si exites el producto con el id recibido
    en tal caso actualiza su informacion a excepcion de su id y o por otro lado si no encuentra un
    producto returna null
     */
    @Override
    @Transactional
    public Producto updateById(Producto producto, Long id) {
        Optional<Producto> optional = findById(id);
        if (!optional.isPresent()){
            return null;
        }
        Producto producto1 = optional.get();
        producto1.setNombre(producto.getNombre());
        producto1.setPrecio(producto.getPrecio());
        producto1.setStock(producto.getStock());

        return productoRepository.save(producto1);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        Optional<Producto> optional = findById(id);
        if (!optional.isPresent()){
            return false;
        }
        productoRepository.deleteById(id);
        return true;
    }
}
