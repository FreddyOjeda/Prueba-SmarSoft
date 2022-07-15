package com.prueba.app.Controller;

import com.prueba.app.Entity.Factura;
import com.prueba.app.Service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/*
Esta clase es la encargada de controlar las peticiones http realizadas por el componente de front
Especificamente las peticiones correspondientes con la entidad de facturas
para ello se utilizan las etiquetas restcontroller y @requesmaping a cuya etiqueta se le añade
la url que queremos que el front utlice para que acceda  a este servicio
 */
@RestController
@RequestMapping("/invoices")
public class FacturaController {

    //se crea la instancia a los servicios del crud de  la entidad de facturas
    @Autowired
    private FacturaService facturaService;

    /*este metodo se encarga de recibir las peticiones de creacion (post) por eso se utiliza
    la etiquete postmaping para aclarar el tipo de peticion y ademas se le incluye la etiqueta @crossOrigin
    para indicarle que permita el acceso desde cualquier servidor
    * */
    @CrossOrigin
    @PostMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Factura create(@PathVariable Long id , @RequestBody Factura factura) throws Exception{
        return facturaService.create(id,factura);
    }

    /*este metodo se encarga retornar todas las facturas creados (get) por eso se utiliza
   la etiquete getmaping para aclarar el tipo de peticion y ademas se le incluye la etiqueta @crossOrigin
   para indicarle que permita el acceso desde cualquier servidor
   * */
    @CrossOrigin
    @GetMapping
    public ResponseEntity<?> readAll(){
        return ResponseEntity.status(HttpStatus.OK).body(facturaService.findAll());
    }

    /*este metodo se encarga de retornar una factura especifico por medio de un id (get) por eso se utiliza
    la etiquete postmaping para aclarar el tipo de peticion y ademas se le incluye la etiqueta @crossOrigin
    para indicarle que permita el acceso desde cualquier servidor, ademas para este caso especial se recibe una variable
    de ruta @pathvariable por ente se le añade el /{id} a la url principal
    * */
    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<?> readById(@PathVariable Long id){
        Optional<Factura> optional = facturaService.findById(id);
        if (!optional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La Factura No Se Encontró En La Base De Datos.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(optional);
    }

    /*este metodo se encarga de actualizar la informacion de una factura especifico por medio de un id (put) por eso se utiliza
    la etiquete putmaping para aclarar el tipo de peticion y ademas se le incluye la etiqueta @crossOrigin
    para indicarle que permita el acceso desde cualquier servidor, ademas para este caso especial se recibe una variable
    de ruta @pathvariable por ente se le añade el /{id} a la url principal y ademas recibe un body @requestbody
    que es el json que contiene la nueva informacion de la factura que se desea modificar
    * */
    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Factura factura,@PathVariable Long id){
        Factura optional = facturaService.updateById(factura,id);
        if (optional==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La Factura No Se Encontró En La Base De Datos.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(optional);
    }

    /*este metodo se encarga de eliminar una factura especifico por medio de un id (put) por eso se utiliza
    la etiquete deletemaping para aclarar el tipo de peticion y ademas se le incluye la etiqueta @crossOrigin
    para indicarle que permita el acceso desde cualquier servidor, ademas para este caso especial se recibe una variable
    de ruta @pathvariable por ente se le añade el /{id} a la url principal
    * */
    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        boolean optional = facturaService.deleteById(id);
        if (optional==false){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La Factura No Se Encontró En La Base De Datos.");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Factura eliminada satisfactoriamente");
    }

}
