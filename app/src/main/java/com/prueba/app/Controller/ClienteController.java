package com.prueba.app.Controller;

import com.prueba.app.Entity.Cliente;
import com.prueba.app.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/*
Esta clase es la encargada de controlar las peticiones http realizadas por el componente de front
Especificamente las peticiones correspondientes con la entidad de cliente
para ello se utilizan las etiquetas restcontroller y @requesmaping a cuya etiqueta se le añade
la url que queremos que el front utlice para que acceda  a este servicio
 */

@RestController
@RequestMapping("/costumers")
public class ClienteController {

    //se crea la instancia a los servicios del crud de  la entidad de usuarios
    @Autowired
    private ClienteService clienteService;

    /*este metodo se encarga de recibir las peticiones de creacion (post) por eso se utiliza
    la etiquete postmaping para aclarar el tipo de peticion y ademas se le incluye la etiqueta @crossOrigin
    para indicarle que permita el acceso desde cualquier servidor
    * */
    @PostMapping
    @CrossOrigin
    public ResponseEntity<?> create(@RequestBody Cliente cliente){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.create(cliente));
    }

    /*este metodo se encarga retornar todos los clientes creados (get) por eso se utiliza
    la etiquete getmaping para aclarar el tipo de peticion y ademas se le incluye la etiqueta @crossOrigin
    para indicarle que permita el acceso desde cualquier servidor
    * */
    @CrossOrigin
    @GetMapping
    public ResponseEntity<?> readAll(){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findAll());
    }


    /*este metodo se encarga de retornar un cliente especifico por medio de un id (get) por eso se utiliza
    la etiquete postmaping para aclarar el tipo de peticion y ademas se le incluye la etiqueta @crossOrigin
    para indicarle que permita el acceso desde cualquier servidor, ademas para este caso especial se recibe una variable
    de ruta @pathvariable por ente se le añade el /{id} a la url principal
    * */
    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<?> readById(@PathVariable Long id){
        Optional<Cliente> optional = clienteService.findById(id);
        if (!optional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El Cliente No Se Encontró En La Base De Datos.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(optional);
    }

    /*este metodo se encarga de actualizar la informacion de un cliente especifico por medio de un id (put) por eso se utiliza
    la etiquete putmaping para aclarar el tipo de peticion y ademas se le incluye la etiqueta @crossOrigin
    para indicarle que permita el acceso desde cualquier servidor, ademas para este caso especial se recibe una variable
    de ruta @pathvariable por ente se le añade el /{id} a la url principal y ademas recibe un body @requestbody
    que es el json que contiene la nueva informacion del cliente que se desea modificar
    * */
    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Cliente cliente,@PathVariable Long id){
        Cliente optional = clienteService.updateById(cliente,id);
        if (optional==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El Cliente No Se Encontró En La Base De Datos.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(optional);
    }

    /*este metodo se encarga de eliminar un cliente especifico por medio de un id (put) por eso se utiliza
    la etiquete deletemaping para aclarar el tipo de peticion y ademas se le incluye la etiqueta @crossOrigin
    para indicarle que permita el acceso desde cualquier servidor, ademas para este caso especial se recibe una variable
    de ruta @pathvariable por ente se le añade el /{id} a la url principal
    * */
    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        boolean optional = clienteService.deleteById(id);
        if (optional==false){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El Cliente No Se Encontró En La Base De Datos.");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Cliente eliminado satisfactoriamente");
    }

}
