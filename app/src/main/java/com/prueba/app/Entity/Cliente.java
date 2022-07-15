package com.prueba.app.Entity;

import javax.persistence.*;

/*
Esta es la entidad de clientes con la cual se crear la tabla en la base de datos, aqui se especifican
los datos del cliente ademas de las caracteristicas de cada uno...
Las etiquetas utilizadas para esta clase son:
@entity: para indicarle a springboot que se trata de una entidad que se debe crear en la base de datos
@Table: se utiliza para añadirle caracteristicas a la tabla como el nombre que tendra en la base de datos
@id : se apica sobre un atributo para indicarle que va a ser la llave primaria
@generatedValue: tiene varias funciones pero en este caso se utiliza para indicarle que la asignacion se automatica
 */
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cliente;
    private String nombre;
    private String apellido;
    private String direccion;
    private String fecha_nacimiento;
    private double telefono;
    private String email;

    public Long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Long id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public double getTelefono() {
        return telefono;
    }

    public void setTelefono(double telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
