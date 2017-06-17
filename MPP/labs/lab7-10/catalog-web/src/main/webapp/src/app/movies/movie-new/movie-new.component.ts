import {Component, OnInit, Input} from '@angular/core';
import {Movie} from "../shared/movie.model";
import {MovieService} from "../shared/movie.service";
import {Location} from "@angular/common";

@Component({
  selector: 'app-movie-new',
  templateUrl: './movie-new.component.html',
  styleUrls: ['./movie-new.component.css']
})
export class MovieNewComponent{
  @Input() movie: Movie;

  constructor(private movieService: MovieService,
              private location: Location) {
  }

  goBack(): void {
    this.location.back();
  }

  save(name, director, genre, availableCopies): void{
    if (!this.isValid( name,director, genre, availableCopies)) {
      console.log("all fields are required ");
      alert("all fields are required;");
      return;
    }
    this.movieService.create(name, director, genre, availableCopies).subscribe(_ => this.goBack());
  }
  private isValid(name, director, genre, avc) {
    if ( !name || !avc || !genre || !director) {
      console.log("all fields are required");
      return false;
    }
    if (!Number.isInteger(Number(avc))) {
      console.log("groupNumber has to be an int");
      return false;
    }
    return true;
  }
}
