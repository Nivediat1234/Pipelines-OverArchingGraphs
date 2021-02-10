import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';

import { UploadedComponent } from './uploaded/uploaded.component';
import { AuthService } from './auth.service';
import { HttpClientModule } from '@angular/common/http';
import { D3AppComponent } from './d3-app/d3-app.component';
import { MergeComponent } from './merge/merge.component';
import { D3AppMergeComponent } from './d3-app-merge/d3-app-merge.component';
import { ContactComponent } from './contact/contact.component';
import { WorkComponent } from './work/work.component';


@NgModule({
  declarations: [
    
    AppComponent,
    HomeComponent,
    UploadedComponent,
    D3AppComponent,
    MergeComponent,
    D3AppMergeComponent,
    ContactComponent,
    WorkComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,HttpClientModule
  ],
  providers: [AuthService],
  bootstrap: [AppComponent]
})
export class AppModule { }
