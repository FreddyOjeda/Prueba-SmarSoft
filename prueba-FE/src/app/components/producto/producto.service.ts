import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { Producto } from './producto';
import { DatePipe } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  private url: string = "http://localhost:8090/products"

  pipe = new DatePipe('en-US');
  todayWithPipe = null;


  constructor(private http: HttpClient) { }

  //metodo para obtener los producto
  producto(): Observable<Producto[]> {


    return this.http.get<Producto[]>(this.url);

  }

  //metodo para crear los producto
  create(producto: Producto): Observable<Producto> {
    return this.http.post<Producto>(this.url, producto);
  }

  //metodo para obtener un solo producto
  get(id_producto: number): Observable<Producto> {

    return this.http.get<Producto>(this.url + '/' + id_producto);
  }

  //metodo para modificar el producto
  update(producto: Producto): Observable<Producto> {
    return this.http.put<Producto>(this.url, producto);
  }

  //metodo para eliminar el producto
  delete(id_producto: number): Observable<Producto> {

    return this.http.delete<Producto>(this.url + '/' + id_producto);
  }
}
