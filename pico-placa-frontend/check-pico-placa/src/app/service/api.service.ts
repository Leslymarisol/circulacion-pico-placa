import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PicoPlacaI } from '../model/pico-placa-i';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(
    private readonly http:HttpClient
  ) { }


  postPicoPlacaData(form:PicoPlacaI):Observable<any>{
    const url = environment.urlAPI
    return this.http
      .post(url, form, {responseType:'text'})
  }
}
