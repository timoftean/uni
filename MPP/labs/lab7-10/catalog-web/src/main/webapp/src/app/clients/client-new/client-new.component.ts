import {Component, OnInit, Input} from '@angular/core';
import {Client} from "../shared/client.model";
import {ClientService} from "../shared/client.service";
import {Location} from '@angular/common';

@Component({
  moduleId: module.id,
  selector: 'app-client-new',
  templateUrl: './client-new.component.html',
  styleUrls: ['./client-new.component.css']
})
export class ClientNewComponent  {
  @Input() client: Client;

  constructor(private clientService: ClientService,
              private location: Location) {
  }

  goBack(): void {
    this.location.back();
  }


  save(name, cnp): void {
     if (!this.isValid( name, cnp)) {
      console.log("all fields are required ");
      alert("all fields are required; cnp has to be an int");
      return;
    }
    this.clientService.create(name, cnp)
      .subscribe(_ => this.goBack());
  }

  private isValid(name, cnp) {
    if ( !name || !cnp) {
      console.log("all fields are required");
      return false;
    }
    if (!Number.isInteger(Number(cnp))) {
      console.log("groupNumber has to be an int");
      return false;
    }
    return true;
  }
}
