import { NgModule } from '@angular/core';
import { MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatTableModule} from '@angular/material/table';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatCardModule} from '@angular/material/card';
import { YouTubePlayerModule } from "@angular/youtube-player";

const materialcompnents =[
  MatButtonModule,
  MatToolbarModule,
  MatFormFieldModule,
  MatInputModule,
  MatTableModule,
  MatIconModule,
  MatButtonToggleModule,
  MatGridListModule,
  MatCardModule,
  YouTubePlayerModule
]

@NgModule({
  imports: [materialcompnents],
  exports: [materialcompnents]
})
export class MaterialModule { }
