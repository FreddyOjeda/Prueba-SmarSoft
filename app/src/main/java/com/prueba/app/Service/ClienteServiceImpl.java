package com.prueba.app.Service;

import com.prueba.app.Entity.Cliente;
import com.prueba.app.Repository.ClienteRepository;
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
public class ClienteServiceImpl implements  ClienteService{

    //se hace la instancia por inyeccion del repositorio de cliente
    @Autowired
    ClienteRepository clienteRepository;

    @Override
    @Transactional(readOnly = true)
    public ArrayList<Cliente> findAll() {
        return (ArrayList<Cliente>) clienteRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    @Transactional
    public Cliente create(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    /*
    en este metodo se recibe el cliente con la informacion actualizada y su id,
    el codigo se encarga de buscar y validar si exites el cliente con el id recibido
    en tal caso actualiza su informacion a excepcion de su id y o por otro lado si no encuentra un
    cliente returna null
     */
    @Override
    @Transactional
    public Cliente updateById(Cliente cliente, Long id) {
        Optional<Cliente> optional = findById(id);
        if (!optional.isPresent()){
            return null;
        }
        Cliente cliente1 = optional.get();
        cliente1.setNombre(cliente.getNombre());
        cliente1.setApellido(cliente.getApellido());
        cliente1.setTelefono(cliente.getTelefono());
        cliente1.setDireccion(cliente.getDireccion());
        cliente1.setFecha_nacimiento(cliente.getFecha_nacimiento());
        cliente1.setEmail(cliente.getEmail());

        return clienteRepository.save(cliente1);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        Optional<Cliente> optional = findById(id);
        if (!optional.isPresent()){
            return false;
        }
        clienteRepository.deleteById(id);
        return true;
    }
}
