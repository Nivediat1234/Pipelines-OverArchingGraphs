import { ContactComponent } from './contact/contact.component';
import { MergeComponent } from './merge/merge.component';
import { D3AppComponent } from './d3-app/d3-app.component';


import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { UploadedComponent } from './uploaded/uploaded.component';
import { D3AppMergeComponent } from './d3-app-merge/d3-app-merge.component';
import { WorkComponent } from './work/work.component';


const routes: Routes = [{
  path:'home' , component:HomeComponent
},
{
  path:'',component:HomeComponent,pathMatch:'full'
},{
  path:'upload', component:UploadedComponent
},
{
  path:'D3App',component:D3AppComponent
},
{
  path:'merge',component:MergeComponent
},
{
  path:'D3AppMerge',component:D3AppMergeComponent
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
