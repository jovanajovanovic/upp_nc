import { Component, OnInit } from '@angular/core';
import { MagazineService } from 'src/app/service/magazine.service';

@Component({
  selector: 'app-view-magazines',
  templateUrl: './view-magazines.component.html',
  styleUrls: ['./view-magazines.component.css']
})
export class ViewMagazinesComponent implements OnInit {

  magazines = [];
  constructor(private magazineService: MagazineService) { }

  ngOnInit() {
    this.magazineService.getAll().subscribe(
      res => {
        this.magazines = res;
      },
      err => {
        alert("Error occured -> get magazines");
      }
    )
  }

}
