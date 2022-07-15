package com.prueba.app.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

/*
Esta es la entidad de clientes con la cual se crear la tabla en la base de datos, aqui se especifican
los datos del detalle ademas de las caracteristicas de cada uno...
Las etiquetas utilizadas para esta clase son:
@entity: para indicarle a springboot que se trata de una entidad que se debe crear en la base de datos
@Table: se utiliza para añadirle caracteristicas a la tabla como el nombre que tendra en la base de datos
@id : se apica sobre un atributo para indicarle que va a ser la llave primaria
@generatedValue: tiene varias funciones pero en este caso se utiliza para indicarle que la asignacion se automatica
@manytoone: para establecer una relacion entre las tablas de mucho a uno
@joincolumn: para darle mas especificaciones a la relacion que se va a crear
@ondelete: ayuda para que cuando un objeto de la classe relacion sea eliminado se eliminen tambien los objetos de la
otra tabla que esten relacionados
@Jsonignore:es para evitar no recibir el json en casso de algun parametro diferente
 */
@Entity
@Table(name = "detalles")
public class Detalle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num_detalle;

    private int cantidad;
    private double precio;

    @ManyToOne
    // Mediante la anotación @JoinColumn es posible personalizar las columnas que
    // será utilizadas como uniones con otras tablas.
    @JoinColumn(name = "id_factura", referencedColumnName = "num_factura",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Factura id_factura;

    @ManyToOne
    // Mediante la anotación @JoinColumn es posible personalizar las columnas que
    // será utilizadas como uniones con otras tablas.
    @JoinColumn(name = "id_producto", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Producto id_producto;

    public Long getNum_detalle() {
        return num_detalle;
    }

    public void setNum_detalle(Long num_detalle) {
        this.num_detalle = num_detalle;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @JsonIgnore
    public Factura getId_factura() {
        return id_factura;
    }

    @JsonIgnore
    public void setId_factura(Factura id_factura) {
        this.id_factura = id_factura;
    }

    @JsonIgnore
    public Producto getId_producto() {
        return id_producto;
    }

    @JsonIgnore
    public void setId_producto(Producto id_producto) {
        this.id_producto = id_producto;
    }
}
