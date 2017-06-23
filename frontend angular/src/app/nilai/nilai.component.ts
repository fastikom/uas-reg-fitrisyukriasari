import { Component, OnInit } from '@angular/core';
import {NilaiService} from "./nilai.service";
import {Nilai} from "./nilai";

@Component({
  selector: 'app-nilai',
  templateUrl: './nilai.component.html',
  styleUrls: ['./nilai.component.css']
})
export class NilaiComponent implements OnInit {
  nilai: Nilai[];
  errMesg: any;

  constructor(private nilaiService: NilaiService) { }

  ngOnInit() {
    this.getNilai();
  }

  getNilai(){
    this.nilaiService.getNilai()
      .subscribe(
        nilai => this.nilai = nilai,
        error => this.errMesg = <any>error
      )
  }

}
