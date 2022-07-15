import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { Detalle } from './detalle';
import { DatePipe } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class DetalleService {

  private url: string = "http://localhost:8090/details"
  pipe = new DatePipe('en-US');
  todayWithPipe = null;


  constructor(private http: HttpClient) { }

  //metodo para obtener los detalle
  detalle(): Observable<Detalle[]> {


    return this.http.get<Detalle[]>(this.url);

  }

  //metodo para crear los detalle
  create(detalle: Detalle): Observable<Detalle> {
    return this.http.post<Detalle>(this.url+'/'+detalle.id_factura+'/'+detalle.id_producto, detalle);
  }

  //metodo para obtener un solo detalle
  get(id_detalle: number): Observable<Detalle> {

    return this.http.get<Detalle>(this.url + '/' + id_detalle);
  }

  //metodo para modificar el detalle
  update(detalle: Detalle): Observable<Detalle> {
    return this.http.put<Detalle>(this.url, detalle);
  }

  //metodo para eliminar el detalle
  delete(id_detalle: number): Observable<Detalle> {

    return this.http.delete<Detalle>(this.url + '/' + id_detalle);
  }
}
