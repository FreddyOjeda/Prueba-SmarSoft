import { Factura } from "../factura/factura";

export class Cliente {

    id_cliente: number;
    nombre: string;
    apellido: string;
    direccion:string;
    fecha_nacimiento: number;
    telefono: number;
    email: string;
    

    detalles?: Factura[];




}
