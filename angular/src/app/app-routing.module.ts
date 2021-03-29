import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdmindashboardComponent } from './admin/admindashboard.component';

import { LoginComponent } from './login/login.component';
import { PagenotfoundComponent } from './pagenotfound/pagenotfound.component';
import { ResourceComponent } from './resource/resource.component';
import { SignupComponent } from './signup/signup.component';
import { UserComponent } from './user/user.component';


const routes: Routes = [
  {path :'' , redirectTo:'/login', pathMatch:'full'},
  {path : 'login' ,component:LoginComponent},
  {path : 'signup' ,component:SignupComponent},
  {path :'admindashboard',component:AdmindashboardComponent},
  {path : 'resource',component:ResourceComponent},
  {path : 'user',component:UserComponent},
  {path :'**',component:PagenotfoundComponent}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingcomponents=[]
