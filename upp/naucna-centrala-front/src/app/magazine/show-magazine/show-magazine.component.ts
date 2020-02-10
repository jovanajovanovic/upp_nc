import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MagazineService } from 'src/app/service/magazine.service';

@Component({
  selector: 'app-show-magazine',
  templateUrl: './show-magazine.component.html',
  styleUrls: ['./show-magazine.component.css']
})
export class ShowMagazineComponent implements OnInit {

  private issn;
  private magazine;

  constructor(private route: ActivatedRoute, private magazineService: MagazineService) { 
    this.route.params.subscribe(
      params => {
        this.issn = String(params['id']);
        magazineService.getMagazine(this.issn).subscribe(
          data => {
            this.magazine = data;
     //       alert(this.magazine);
          },
          err => {
            alert("Magazine whit " + this.issn + " does not exist!");
          }
        )
      }
    );
  }

  ngOnInit() {
  }

}
