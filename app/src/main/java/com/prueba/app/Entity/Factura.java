package com.prueba.app.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/*
Esta es la entidad de clientes con la cual se crear la tabla en la base de datos, aqui se especifican
los datos de la factura ademas de las caracteristicas de cada uno...
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
@Table(name = "facturas")
public class Factura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num_factura;

    private String fecha;

    @ManyToOne
    // Mediante la anotación @JoinColumn es posible personalizar las columnas que
    // será utilizadas como uniones con otras tablas.
    @JoinColumn(name = "id_cliente", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Cliente id_cliente;

    public Long getNum_factura() {
        return num_factura;
    }

    public void setNum_factura(Long num_factura) {
        this.num_factura = num_factura;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @JsonIgnore
    public Cliente getId_cliente() {
        return id_cliente;
    }

    @JsonIgnore
    public void setId_cliente(Cliente id_cliente) {
        this.id_cliente = id_cliente;
    }
}
