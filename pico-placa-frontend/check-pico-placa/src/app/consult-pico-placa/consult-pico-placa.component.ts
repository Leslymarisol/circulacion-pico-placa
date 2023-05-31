import { Component, OnInit } from '@angular/core';
import { PicoPlacaI } from '../model/pico-placa-i';
import { ApiService } from '../service/api.service';


@Component({
  selector: 'app-consult-pico-placa',
  templateUrl: './consult-pico-placa.component.html',
  styleUrls: ['./consult-pico-placa.component.css']
})
export class ConsultPicoPlacaComponent implements OnInit {




  dataPicoPlaca: PicoPlacaI = {
    lastDigit: undefined,
    date: '',
    hour: ''
  }

  message = '';
  mostrar = false;


  constructor(
    private readonly api: ApiService,

  ) { }



  ngOnInit(): void {
    console.log('DESDE EL INIT',this.dataPicoPlaca);
  }



  mostrarContenido() {
    this.mostrar = true;
  }

  saveData() {

    const sendData = {
      lastDigit: Number(this.dataPicoPlaca.lastDigit?.toString().slice(-1)),
      date: this.dataPicoPlaca.date,
      hour: this.dataPicoPlaca.hour
    }

    this.api.postPicoPlacaData(sendData)
      .subscribe({
        next: (data: any) => {
          console.log('Los datos se han enviado', data)

          this.message = data
          
        },
        error: (error) => {

        }
      })

      this.mostrarContenido();

  }


  


}
