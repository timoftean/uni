import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ClientsComponent} from "./clients/clients.component";
import {ClientDetailComponent} from "./clients/client-detail/client-detail.component";
import {MoviesComponent} from "./movies/movies.component";
import {MovieDetailComponent} from "./movies/movie-detail/movie-detail.component";
import {RentsComponent} from "./rents/rents.component";
import {ClientNewComponent} from "./clients/client-new/client-new.component";
import {MovieNewComponent} from './movies/movie-new/movie-new.component';
import {RentMoviesComponent} from "./rent-movies/rent-movies.component";


const routes: Routes = [

  { path: '', redirectTo: '/', pathMatch: 'full' },

  {path:'movies',component:MoviesComponent},
  {path:'movie/detail/:id',component:MovieDetailComponent},
  {path:'movie/new', component: MovieNewComponent},

  {path:'rents',component:RentsComponent},

  { path: 'clients',     component: ClientsComponent },
  { path: 'client/detail/:id', component: ClientDetailComponent},
  { path: 'client/new', component: ClientNewComponent},

  {path: 'rentMovie', component: RentMoviesComponent},
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
