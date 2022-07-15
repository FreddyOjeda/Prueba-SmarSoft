package com.prueba.app.Service;

import com.prueba.app.Entity.FactFront;
import com.prueba.app.Entity.Factura;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

/*
se utiliza un patron de fachada para que desde el controlador a la logica como tal de un servicio sino que
por medio de una interface acceda a las funcionalidades del servicio, en cuya interfaz unicamente se
definen los metodos que ofrece el servicio pero ser5an implentados en una lcase aparte
 */
public interface FacturaService {

    public ArrayList<FactFront> findAll();
    public Optional<Factura> findById(Long id);
    public Factura create(Factura factura);

    @Transactional
    Factura create(Long clienteId, Factura factura) throws Exception;

    public Factura updateById(Factura factura, Long id);
    public boolean deleteById(Long id);
}
