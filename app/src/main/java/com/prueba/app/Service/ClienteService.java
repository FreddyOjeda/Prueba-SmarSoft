package com.prueba.app.Service;

import com.prueba.app.Entity.Cliente;

import java.util.ArrayList;
import java.util.Optional;

/*
se utiliza un patron de fachada para que desde el controlador a la logica como tal de un servicio sino que
por medio de una interface acceda a las funcionalidades del servicio, en cuya interfaz unicamente se
definen los metodos que ofrece el servicio pero ser5an implentados en una lcase aparte
 */
public interface ClienteService {

    public ArrayList<Cliente> findAll();
    public Optional<Cliente> findById(Long id);
    public Cliente create(Cliente cliente);
    public Cliente updateById(Cliente cliente, Long id);
    public boolean deleteById(Long id);
}
