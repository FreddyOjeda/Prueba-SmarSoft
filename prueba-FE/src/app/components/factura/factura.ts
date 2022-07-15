import { Cliente } from "../cliente/cliente";

export class Factura {

    num_factura: number;
    id_cliente: number;
    fecha: number;

    detalles?: Cliente[];


}
