package com.prueba.app.Service;

import com.prueba.app.Entity.DetFront;
import com.prueba.app.Entity.Detalle;

import java.util.ArrayList;
import java.util.Optional;


/*
se utiliza un patron de fachada para que desde el controlador a la logica como tal de un servicio sino que
por medio de una interface acceda a las funcionalidades del servicio, en cuya interfaz unicamente se
definen los metodos que ofrece el servicio pero ser5an implentados en una lcase aparte
 */
public interface DetalleService {

    public ArrayList<DetFront> findAll();
    public Optional<Detalle> findById(Long id);
    public Detalle create(Long id_fact, Long id_prod, Detalle detalle) throws Exception;
    public Detalle updateById(Detalle detalle,Long id);
    public boolean deleteById(Long id);
}
