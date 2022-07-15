package com.prueba.app.Entity;


/*
Esta clase es una variacion de la clase modelo de detalle, se crea con el proposito de que sea compatible
con las clases creadas en el front mas especificamente de hacer que las variables id_factura y id_producto
no sean tipo factura y producto sino tipo long y asi facilitar su interpretacion y manejo en el front
 */
public class DetFront {

    Long num_detalle;
    int cantidad;
    double precio;
    Long id_factura;
    Long id_producto;

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

    public Long getId_factura() {
        return id_factura;
    }

    public void setId_factura(Long id_factura) {
        this.id_factura = id_factura;
    }

    public Long getId_producto() {
        return id_producto;
    }

    public void setId_producto(Long id_producto) {
        this.id_producto = id_producto;
    }
}
