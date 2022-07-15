package com.prueba.app.Service;

import com.prueba.app.Entity.DetFront;
import com.prueba.app.Entity.Detalle;
import com.prueba.app.Entity.Factura;
import com.prueba.app.Entity.Producto;
import com.prueba.app.Repository.DetalleRepository;
import com.prueba.app.Repository.FacturaRepository;
import com.prueba.app.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
Como esta ya es el servico logico se utiliza la etiqueta  @Service para indicar que es un servicio
como tal como esta es la implentaciond de un intarfaz todos los metodos que hay en esta clase estan encapsulados
porque ya estan definidos en la interfaz implentada
 */
@Service
public class DetalleServiceImpl implements  DetalleService{

    @Autowired
    private ProductoService productoService;

    //se hace la instancia por inyeccion del repositorio de detalle
    @Autowired
    DetalleRepository detalleRepository;

    //se hace la instancia por inyeccion del repositorio de factura
    @Autowired
    FacturaRepository facturaRepository;

    //se hace la instancia por inyeccion del repositorio de producto
    @Autowired
    ProductoRepository productoRepository;

    /*
    este metodo se encarga de retornar una lista on los detalles, para esto se utiliza la clase modelo detFront
    para cambiar los respectivos datos tal como se documenta en la clase modelo
     */
    @Override
    @Transactional(readOnly = true)
    public ArrayList<DetFront> findAll() {
        List<Detalle> detalles = detalleRepository.findAll();
        ArrayList<DetFront> lista = new ArrayList<>();
        for (int i = 0; i < detalles.size(); i++) {
            DetFront front = new DetFront();
            front.setNum_detalle(detalles.get(i).getNum_detalle());
            front.setCantidad(detalles.get(i).getCantidad());
            front.setId_factura(detalles.get(i).getId_factura().getNum_factura());
            front.setPrecio(detalles.get(i).getPrecio());
            front.setId_producto(detalles.get(i).getId_producto().getId_producto());
            lista.add(front);
        }
        return lista;
    }

    @Override
    @Transactional
    public Optional<Detalle> findById(Long id) {
        return detalleRepository.findById(id);
    }


    /*
    pára crear un detalle primero se debe validar que el producto y la factura que se recibieron existan
    de lo contrario se arrojaria una excepcion, por otro lado si si exiten los dos objetos se debe validar
    si la cantidad solicitada en el detalle sea menor al stock disponible
     */
    @Override
    @Transactional
    public Detalle create(Long id_fact,Long id_prod,Detalle detalle) throws Exception {
        Optional<Factura> optionalFactura = facturaRepository.findById(id_fact);
        Optional<Producto> optionalProducto = productoRepository.findById(id_prod);

        if (!optionalFactura.isPresent()) {
            throw new Exception("Factura with id " + id_fact + " does not exist");
        }else if (!optionalProducto.isPresent()) {
            throw new Exception("Producto with id " + id_fact + " does not exist");
        }

        Factura factura = optionalFactura.get();
        Producto producto= optionalProducto.get();
        if (detalle.getCantidad()>producto.getStock()){
            throw new Exception("La cantidad solicitada exede el stock disponible");
        }
        producto.setStock(producto.getStock()-detalle.getCantidad());
        productoService.updateById(producto,id_prod);

        detalle.setId_producto(producto);
        detalle.setId_factura(factura);
        detalle.setPrecio(producto.getPrecio()*detalle.getCantidad());

        Detalle detalle1=detalleRepository.save(detalle);

        return detalle1;
    }

    /*
    en este metodo se recibe el detalle con la informacion actualizada y su id,
    el codigo se encarga de buscar y validar si exites el detalle con el id recibido
    en tal caso actualiza su informacion a excepcion de su id y o por otro lado si no encuentra un
    detalle returna null
     */
    @Override
    @Transactional
    public Detalle updateById(Detalle detalle, Long id)  {
        Optional<Detalle> optional = findById(id);
        if (!optional.isPresent()){
            return null;
        }
        Detalle detalle1 = optional.get();
        detalle1.setCantidad(detalle.getCantidad());
        detalle1.setId_producto(detalle.getId_producto());
        detalle1.setPrecio(detalle.getPrecio());
        detalle1.setId_factura(detalle.getId_factura());
        return detalleRepository.save(detalle1);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        Optional<Detalle> optional = findById(id);
        if (!optional.isPresent()){
            return false;
        }
        detalleRepository.deleteById(id);
        return true;
    }
}
