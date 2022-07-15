package com.prueba.app.Controller;

import com.prueba.app.Entity.Producto;
import com.prueba.app.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/*
Esta clase es la encargada de controlar las peticiones http realizadas por el componente de front
Especificamente las peticiones correspondientes con la entidad de productos
para ello se utilizan las etiquetas restcontroller y @requesmaping a cuya etiqueta se le añade
la url que queremos que el front utlice para que acceda  a este servicio
 */
@RestController
@RequestMapping("/products")
public class ProductoController {

    //se crea la instancia a los servicios del crud de  la entidad de productos
    @Autowired
    private ProductoService productoService;

    /*este metodo se encarga de recibir las peticiones de creacion (post) por eso se utiliza
    la etiquete postmaping para aclarar el tipo de peticion y ademas se le incluye la etiqueta @crossOrigin
    para indicarle que permita el acceso desde cualquier servidor
    * */
    @CrossOrigin
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Producto producto){
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.create(producto));
    }

    /*este metodo se encarga retornar todos los productos creados (get) por eso se utiliza
    la etiquete getmaping para aclarar el tipo de peticion y ademas se le incluye la etiqueta @crossOrigin
    para indicarle que permita el acceso desde cualquier servidor
    * */
    @CrossOrigin
    @GetMapping
    public ResponseEntity<?> readAll(){
        return ResponseEntity.status(HttpStatus.OK).body(productoService.findAll());
    }

    /*este metodo se encarga de retornar un producto especifico por medio de un id (get) por eso se utiliza
    la etiquete postmaping para aclarar el tipo de peticion y ademas se le incluye la etiqueta @crossOrigin
    para indicarle que permita el acceso desde cualquier servidor, ademas para este caso especial se recibe una variable
    de ruta @pathvariable por ente se le añade el /{id} a la url principal
    * */
    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<?> readById(@PathVariable Long id){
        Optional<Producto> optional = productoService.findById(id);
        if (!optional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El Producto No Se Encontró En La Base De Datos.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(optional);
    }

    /*este metodo se encarga de actualizar la informacion de un producto especifico por medio de un id (put) por eso se utiliza
    la etiquete putmaping para aclarar el tipo de peticion y ademas se le incluye la etiqueta @crossOrigin
    para indicarle que permita el acceso desde cualquier servidor, ademas para este caso especial se recibe una variable
    de ruta @pathvariable por ente se le añade el /{id} a la url principal y ademas recibe un body @requestbody
    que es el json que contiene la nueva informacion del producto que se desea modificar
    * */
    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Producto producto,@PathVariable Long id){
        Producto optional = productoService.updateById(producto,id);
        if (optional==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El Producto No Se Encontró En La Base De Datos.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(optional);
    }

    /*este metodo se encarga de eliminar un producto especifico por medio de un id (put) por eso se utiliza
    la etiquete deletemaping para aclarar el tipo de peticion y ademas se le incluye la etiqueta @crossOrigin
    para indicarle que permita el acceso desde cualquier servidor, ademas para este caso especial se recibe una variable
    de ruta @pathvariable por ente se le añade el /{id} a la url principal
    * */
    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        boolean optional = productoService.deleteById(id);
        if (optional==false){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El Producto No Se Encontró En La Base De Datos.");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Producto eliminado satisfactoriamente");
    }
}
