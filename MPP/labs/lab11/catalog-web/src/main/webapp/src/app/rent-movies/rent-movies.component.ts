import { Component, OnInit } from '@angular/core';
import {Movie} from "../movies/shared/movie.model";
import {Client} from "../clients/shared/client.model";
import {ClientService} from "../clients/shared/client.service";
import {MovieService} from "../movies/shared/movie.service";
import {Location} from '@angular/common';

@Component({
  selector: 'app-rent-movies',
  templateUrl: './rent-movies.component.html',
  styleUrls: ['./rent-movies.component.css']
})

export class RentMoviesComponent {
  errorMessage: string;
  selectedClient: Client;
  showMovies: boolean;
  selectedMovies: Movie[];
  availableMovies: Movie[];

  constructor(private clientService: ClientService,
              private movieService: MovieService,
              private location: Location) {
  }

  goBack(): void {
    this.location.back();
  }

  loadMovies(cnp: number) {
    console.log("here")
    this.showMovies = false;
    if (!cnp) {
      console.log("cnp must not be empty");
      alert("cnp must not be empty");
      return;
    }
    this.loadAvailableMovies();
    this.loadSelectedMovies(cnp);
  }

  private loadSelectedMovies(cnp: number) {
    this.clientService.getClients()
      .subscribe(
        clients => {
          const currentClient = clients.filter(c => c.cnp === cnp);
          //TODO there should be exactly or err; handle errs
          if (currentClient.length === 1) {
            this.selectedClient = currentClient[0];
            this.showMovies = true;
            this.loadSelectedMoviesForClient(this.selectedClient);
          }
        },
        error => this.errorMessage = <any>error);
  }

  private loadSelectedMoviesForClient(client: Client) {
    this.selectedMovies = new Array();
    const movieIds = client.movies;
    if (movieIds) {
      movieIds.forEach(id => {
        this.movieService.getMovie(id)
          .subscribe(
            movie => {
              this.selectedMovies = this.selectedMovies
                .concat([movie]);
            },
            error => this.errorMessage = error);
      });
    }
  }

  private loadAvailableMovies() {
    this.movieService.getMovies()
      .subscribe(
        movies => this.availableMovies = movies,
        error => this.errorMessage = <any>error);
  }

  selectMovies(movie: Movie) {
    const mov = this.selectedMovies.filter(m => m === movie);
    if (mov.length > 0) {
      console.log("movie already selected");
      alert("movie already selected");
      return;
    }
    this.selectedMovies = this.selectedMovies.concat([movie]);
  }

  removeSelectedMovie(movie: Movie) {
    this.selectedMovies = this.selectedMovies.filter(m => m !== movie);
  }

  save() {

    this.selectedClient.movies = this.selectedMovies.map(m => m.id);
    console.log("ok ", this.selectedMovies, this.selectedMovies.map(m => m.id), this.selectedClient.movies);
    this.clientService.update(this.selectedClient)
      .subscribe(_ => this.goBack())
  }
}
