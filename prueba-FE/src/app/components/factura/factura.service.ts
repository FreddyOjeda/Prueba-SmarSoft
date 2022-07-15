import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { Factura } from './factura';
import { DatePipe } from '@angular/common';
import { Cliente } from '../cliente/cliente';
import { map } from 'rxjs/operators';
import { ClienteService } from '../cliente/cliente.service';

@Injectable({
  providedIn: 'root'
})
export class FacturaService {

  private url: string = "http://localhost:8090/invoices"
  private factu:Factura;

  pipe = new DatePipe('en-US');
  todayWithPipe = null;


  constructor(private http: HttpClient) { }

  getClientes(): Observable<Cliente[]> {
    return this.http.get(this.url).pipe(
      map(Facturas => Facturas as Cliente[])
    );
  }

  //metodo para obtener los clientes
  clientes(): Observable<any[]> {
    return this.http.get<any[]>(this.url);

  }

  //metodo para obtener las facturas
  facturas(): Observable<any[]> {
    return this.http.get<any[]>(this.url);

  }

  //metodo para crear las facturas
  create(id:number, factura: Factura): Observable<Factura> {
    console.log(factura);
    return this.http.post<Factura>(this.url+'/'+factura.id_cliente,factura);
  }

  //metodo para obtener un solo factura por id
  get(num_factura: number): Observable<Factura> {

    return this.http.get<Factura>(this.url + '/' + num_factura);
  }

  //metodo para modificar la factura
  update(factura: Factura): Observable<Factura> {
    return this.http.put<Factura>(this.url, factura);
  }

  //metodo para eliminar el factura
  delete(num_factura: number): Observable<Factura> {

    return this.http.delete<Factura>(this.url+ '/' + num_factura);
  }
}
