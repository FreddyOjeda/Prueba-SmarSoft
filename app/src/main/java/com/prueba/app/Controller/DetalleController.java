package com.prueba.app.Controller;

import com.prueba.app.Entity.Detalle;
import com.prueba.app.Service.DetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/*
Esta clase es la encargada de controlar las peticiones http realizadas por el componente de front
Especificamente las peticiones correspondientes con la entidad de detalle de factura
para ello se utilizan las etiquetas restcontroller y @requesmaping a cuya etiqueta se le añade
la url que queremos que el front utlice para que acceda  a este servicio
 */
@RestController
@RequestMapping("/details")
public class DetalleController {

    //se crea la instancia a los servicios del crud de  la entidad de detalles
    @Autowired
    private DetalleService detalleService;

    /*este metodo se encarga de recibir las peticiones de creacion (post) por eso se utiliza
    la etiquete postmaping para aclarar el tipo de peticion y ademas se le incluye la etiqueta @crossOrigin
    para indicarle que permita el acceso desde cualquier servidor
    * */
    @CrossOrigin
    @PostMapping(value = "/{id_fact}/{id_prod}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Detalle create(@PathVariable Long id_fact,@PathVariable Long id_prod, @RequestBody Detalle detalle) throws Exception {
        return detalleService.create(id_fact,id_prod,detalle);
    }

    /*este metodo se encarga retornar todos los detalles creados (get) por eso se utiliza
    la etiquete getmaping para aclarar el tipo de peticion y ademas se le incluye la etiqueta @crossOrigin
    para indicarle que permita el acceso desde cualquier servidor
    * */
    @CrossOrigin
    @GetMapping
    public ResponseEntity<?> readAll(){
        return ResponseEntity.status(HttpStatus.OK).body(detalleService.findAll());
    }

    /*este metodo se encarga de retornar un detalle especifico por medio de un id (get) por eso se utiliza
    la etiquete postmaping para aclarar el tipo de peticion y ademas se le incluye la etiqueta @crossOrigin
    para indicarle que permita el acceso desde cualquier servidor, ademas para este caso especial se recibe una variable
    de ruta @pathvariable por ente se le añade el /{id} a la url principal
    * */
    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<?> readById(@PathVariable Long id){
        Optional<Detalle> optional = detalleService.findById(id);
        if (!optional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El Detalle No Se Encontró En La Base De Datos.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(optional);
    }

    /*este metodo se encarga de actualizar la informacion de un detalle especifico por medio de un id (put) por eso se utiliza
   la etiquete putmaping para aclarar el tipo de peticion y ademas se le incluye la etiqueta @crossOrigin
   para indicarle que permita el acceso desde cualquier servidor, ademas para este caso especial se recibe una variable
   de ruta @pathvariable por ente se le añade el /{id} a la url principal y ademas recibe un body @requestbody
   que es el json que contiene la nueva informacion del detalle que se desea modificar
   * */
    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Detalle detalle,@PathVariable Long id){
        Detalle optional = detalleService.updateById(detalle,id);
        if (optional==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El Detalle No Se Encontró En La Base De Datos.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(optional);
    }

    /*este metodo se encarga de eliminar un det5alle especifico por medio de un id (delete) por eso se utiliza
    la etiquete deletemaping para aclarar el tipo de peticion y ademas se le incluye la etiqueta @crossOrigin
    para indicarle que permita el acceso desde cualquier servidor, ademas para este caso especial se recibe una variable
    de ruta @pathvariable por ente se le añade el /{id} a la url principal
    * */
    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        boolean optional = detalleService.deleteById(id);
        if (optional==false){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El Detalle No Se Encontró En La Base De Datos.");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Detalle eliminado satisfactoriamente");
    }

}
