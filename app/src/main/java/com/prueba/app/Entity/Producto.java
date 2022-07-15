package com.prueba.app.Entity;

import javax.persistence.*;
import java.io.Serializable;

/*
Esta es la entidad de clientes con la cual se crear la tabla en la base de datos, aqui se especifican
los datos del producto ademas de las caracteristicas de cada uno...
Las etiquetas utilizadas para esta clase son:
@entity: para indicarle a springboot que se trata de una entidad que se debe crear en la base de datos
@Table: se utiliza para añadirle caracteristicas a la tabla como el nombre que tendra en la base de datos
@id : se apica sobre un atributo para indicarle que va a ser la llave primaria
@generatedValue: tiene varias funciones pero en este caso se utiliza para indicarle que la asignacion se automatica
 */
@Entity
@Table(name = "productos")
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id_producto;
   private String nombre;
   private double precio;
   private int stock;

    public Long getId_producto() {
        return id_producto;
    }

    public void setId_producto(Long id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
