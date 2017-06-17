import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { ClientsComponent } from './clients/clients.component';
import { RentsComponent } from './rents/rents.component';
import { MoviesComponent } from './movies/movies.component';
import { ClientListComponent } from './clients/client-list/client-list.component';
import { ClientDetailComponent } from './clients/client-detail/client-detail.component';
import {ClientService} from "./clients/shared/client.service";
import {AppRoutingModule} from "./app-routing.module";
import { MovieListComponent } from './movies/movie-list/movie-list.component';
import {MovieDetailComponent} from "./movies/movie-detail/movie-detail.component";
import {RentService} from "./rents/shared/rent.service";
import {MovieService} from "./movies/shared/movie.service";
import { ClientNewComponent } from './clients/client-new/client-new.component';
import { MovieNewComponent } from './movies/movie-new/movie-new.component';
import { RentMoviesComponent } from './rent-movies/rent-movies.component';

@NgModule({
  declarations: [
    AppComponent,
    ClientsComponent,
    RentsComponent,
    MoviesComponent,
    ClientListComponent,
    ClientDetailComponent,
    MovieListComponent,
    MovieDetailComponent,
    ClientNewComponent,
    MovieNewComponent,
    RentMoviesComponent,
   ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule,
  ],
  providers: [ClientService,RentService,MovieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
