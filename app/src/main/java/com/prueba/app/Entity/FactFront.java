package com.prueba.app.Entity;


/*
Esta clase es una variacion de la clase modelo de factura, se crea con el proposito de que sea compatible
con las clases creadas en el front mas especificamente de hacer que la variable id_cliente no sea
tipo Cliente sino tipo long y asi facilitar su interpretacion y manejo en el front
 */
public class FactFront {

    Long num_factura;
    String fecha;
    Long id_cliente;

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

    public Long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Long id_cliente) {
        this.id_cliente = id_cliente;
    }
}
