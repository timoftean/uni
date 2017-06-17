/**
 * Created by Nicu on 4/9/17.
 */
import {Injectable} from '@angular/core';
import {Http, Response, Headers} from "@angular/http";

import {Observable} from "rxjs";
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import {Rent} from "./rent.model";


@Injectable()
export class RentService {
  private rentUrl = 'http://localhost:8080/api/rents';
  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(private http: Http) {
  }

  getRents(clienId: number): Observable<Rent[]> {
    return this.http.get(`${this.rentUrl}/${clienId}`, this.headers)
      .map(this.extractData)
      .catch(this.handleError);
  }

  private extractData(res: Response) {
    let body = res.json();
    return body.rents || {};
  }

  private handleError(error: Response | any) {
    let errMsg: string;
    if (error instanceof Response) {
      const body = error.json() || '';
      const err = body.error || JSON.stringify(body);
      errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
    } else {
      errMsg = error.message ? error.message : error.toString();
    }
    console.error(errMsg);
    return Observable.throw(errMsg);
  }

  saveNocopies(clientId: number, rentIdsNocopies: Object): Observable<Rent[]> {
    const url = `${this.rentUrl}/${clientId}`;
    let rents = this.createRents(clientId, rentIdsNocopies);
    console.log("nocpoies: ",rents);
    console.log("request: ",JSON.stringify({"rents": rents}));
    return this.http
      .put(url, JSON.stringify({"rents": rents}), {headers: this.headers})
      .map(this.extractData)
      .catch(this.handleError);
  }

  private createRents(clientId: number, rentIdsNocopies: Object): Rent[] {
    const arr: Rent[] = [];
    Object.keys(rentIdsNocopies).forEach( movieId=> {
      const r = new Rent(
        clientId,
        parseInt(movieId),
        rentIdsNocopies[movieId]);
      arr.push(r);
    });
    return arr;
  }
}

//
// @Injectable()
// export class RentService {
//   private rentsUrl = 'http://localhost:8080/api/rents';
//   private headers = new Headers({'Content-Type': 'application/json'});
//
//   constructor(private http: Http) {
//   }
//
//   getRents(): Observable<Rent[]> {
//     return this.http.get(this.rentsUrl)
//       .map(this.extractData)
//       .catch(this.handleError);
//   }
//
//   private extractData(res: Response) {
//     let body = res.json();
//     return body.rents || {};
//   }
//
//   private handleError(error: Response | any) {
//     let errMsg: string;
//     if (error instanceof Response) {
//       const body = error.json() || '';
//       const err = body.error || JSON.stringify(body);
//       errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
//     } else {
//       errMsg = error.message ? error.message : error.toString();
//     }
//     console.error(errMsg);
//     return Observable.throw(errMsg);
//   }
//
//   getRent(id: number): Observable<Rent> {
//     console.log("rentId ",id,this.getRents());
//     return this.getRents().map(rents => rents.find(rent => rent.id === id));
//   }
//
//
//   create(clientCnp: number,movieTitle: number, noCopies: number): Observable<Rent> {
//     let rent = {clientCnp, movieTitle, noCopies};
//     return this.http
//       .post(this.rentsUrl, JSON.stringify({"rent": rent}), {headers: this.headers})
//       .map(this.extractData)
//       .catch(this.handleError);
//   }
//
//
//   update(rent): Observable<Rent> {
//     const url = `${this.rentsUrl}/${rent.id}`;
//     return this.http
//       .put(url, JSON.stringify({"rent": rent}), {headers: this.headers})
//       .map(this.extractData)
//       .catch(this.handleError);
//   }
//
//   delete(id: number): Observable<void> {
//     const url = `${this.rentsUrl}/${id}`;
//     return this.http
//       .delete(url, {headers: this.headers})
//       .map(() => null)
//       .catch(this.handleError);
//   }
//
// }
