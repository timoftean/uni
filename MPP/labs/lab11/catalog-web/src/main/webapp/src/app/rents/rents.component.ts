import { Component } from '@angular/core';
import {Rent} from "./shared/rent.model";
import {RentService} from "./shared/rent.service";
import {Location} from '@angular/common';
import {Client} from "../clients/shared/client.model";
import {ClientService} from "../clients/shared/client.service";

@Component({
  selector: 'app-rents',
  templateUrl: './rents.component.html',
  styleUrls: ['./rents.component.css']
})
export class RentsComponent {
  errorMessage: string;
  showMoviesAndNocopies: boolean;
  rents:Rent[];
  selectedClient: Client;

  constructor(private clientService: ClientService,
              private rentService: RentService,
              private location: Location) {

  }

  loadMoviesAndNocopies(cnp:number) {
    this.showMoviesAndNocopies = false;
    if (!cnp) {
      console.log("cnp must not be empty");
      alert("cnp must not be empty");
      return;
    }
    this.loadClientRentsForClient(cnp);
  }

  private loadClientRentsForClient(cnp:number) {
    this.clientService.getClients()
      .subscribe(
        clients => {
          const clientArr = clients.filter(c => c.cnp === cnp);
          //TODO handle errors
          if (clientArr && clientArr.length === 1) {
            this.showMoviesAndNocopies = true;
            const client = clientArr[0];
            this.selectedClient = client;
            this.rentService.getRents(client.id)
              .subscribe(
                rents => this.rents = rents,
                error => this.errorMessage = error)
          } else {
            console.log("clientArr ", clientArr);
          }
        },
        error => this.errorMessage = <any>error);
  }

  save(rentForm) {
    let nocopies = rentForm.form.value;
    console.log("nocopies: ", nocopies);
    this.rentService.saveNocopies(this.selectedClient.id, nocopies)
      .subscribe(_ => this.goBack());
  }

  private goBack(): void {
    this.location.back();
  }
}
