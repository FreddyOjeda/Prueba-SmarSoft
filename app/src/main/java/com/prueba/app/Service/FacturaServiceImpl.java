package com.prueba.app.Service;

import com.prueba.app.Entity.Cliente;
import com.prueba.app.Entity.FactFront;
import com.prueba.app.Entity.Factura;
import com.prueba.app.Repository.ClienteRepository;
import com.prueba.app.Repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

/*
Como esta ya es el servico logico se utiliza la etiqueta  @Service para indicar que es un servicio
como tal como esta es la implentaciond de un intarfaz todos los metodos que hay en esta clase estan encapsulados
porque ya estan definidos en la interfaz implentada
 */
@Service
public class FacturaServiceImpl implements FacturaService{

    //se hace la instancia por inyeccion del repositorio de factura
    @Autowired
    FacturaRepository facturaRepository;

    //se hace la instancia por inyeccion del repositorio de cliente
    @Autowired
    ClienteRepository clienteRepository;


    /*
    este metodo se encarga de retornar una lista con las facturas, para esto se utiliza la clase modelo factFront
    para cambiar los respectivos datos tal como se documenta en la clase modelo
     */
    @Override
    @Transactional(readOnly = true)
    public ArrayList<FactFront> findAll() {
        List<Factura> facturas=facturaRepository.findAll();
        ArrayList<FactFront> lista = new ArrayList<>();
        for (int i = 0; i < facturas.size(); i++) {
            FactFront front = new FactFront();
            front.setNum_factura(facturas.get(i).getNum_factura());
            front.setFecha(facturas.get(i).getFecha());
            front.setId_cliente(facturas.get(i).getId_cliente().getId_cliente());
            lista.add(front);
        }
        return lista;
    }

    @Override
    @Transactional
    public Optional<Factura> findById(Long id) {
        return facturaRepository.findById(id);
    }

    /*
    pára crear una factura primero se debe validar que el cliente que se recibio exista
    de lo contrario se arrojaria una excepcion.
     */
    @Override
    public Factura create(Factura factura) {

        factura.setFecha(String.valueOf(LocalDate.now()));
        return facturaRepository.save(factura);
    }

    @Override
    @Transactional
    public Factura create(Long clienteId, Factura factura) throws Exception {
        Optional<Cliente> byId = clienteRepository.findById(clienteId);
        if (!byId.isPresent()) {
            throw new Exception("Cliente with id " + clienteId + " does not exist");
        }
        Cliente cliente = byId.get();

        factura.setId_cliente(cliente);
        factura.setFecha(String.valueOf(LocalDate.now()));

        Factura factura1 = facturaRepository.save(factura);

        return factura1;
    }

    /*
    en este metodo se recibe la factura con la informacion actualizada y su id,
    el codigo se encarga de buscar y validar si exites la factura con el id recibido
    en tal caso actualiza su informacion a excepcion de su id y o por otro lado si no encuentra un
    a factura returna null
     */
    @Override
    @Transactional
    public Factura updateById(Factura factura, Long id) {
        Optional<Factura> optional = findById(id);
        if (!optional.isPresent()){
            return null;
        }
        Factura factura1 = optional.get();

        factura1.setId_cliente(factura.getId_cliente());
        factura1.setFecha(factura.getFecha());

        return facturaRepository.save(factura1);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        Optional<Factura> optional = findById(id);
        if (!optional.isPresent()){
            return false;
        }

        facturaRepository.deleteById(id);
        return true;
    }
}
