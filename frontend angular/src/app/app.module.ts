import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { NilaiComponent } from './nilai/nilai.component';
import { NilaiService } from './nilai/nilai.service';


@NgModule({
  declarations: [
    AppComponent,
    NilaiComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule
  ],
  providers: [NilaiService],
  bootstrap: [AppComponent]
})
export class AppModule { }
