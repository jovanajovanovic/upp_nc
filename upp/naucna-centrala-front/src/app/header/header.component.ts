import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { UserService } from '../service/user.service';
import { Router } from '@angular/router';
import { RegistrationService } from '../service/registration.service';
import { MagazineService } from '../service/magazine.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  logUser = false;
  role;
  constructor(private userService: UserService, private router: Router,
    private regService: RegistrationService, private magazineService: MagazineService) { }

  ngOnInit() {
    this.logUser = this.userService.isLoggedIn();
    this.role = localStorage.getItem('role');
    //  alert(JSON.stringify(this.loginUser));
  }


  logOut() {
    localStorage.removeItem('user');
    location.reload();
    this.router.navigate(['']);
  }


  registration() {
    alert('RUN REGISTRATION');
    // pozovemo funkciju za pocetak procesa registracije i prebacimo se na izlistavanje svih taskova
    this.regService.runRegistration().subscribe(
      res =>  location.replace('http://localhost:4200/tasks')
    );

  }

  addArticle() {
    alert('RUN PROCESS ADD ARTICLE');
    this.magazineService.startAddArticle().subscribe(
      res =>{
       // this.router.navigate(['/tasks']);
      // location.reload();
      location.replace('http://localhost:4200/tasks');      } 
    );
  }

  addMagazine() {
    alert('RUN ADD MAGAZINE PROCESS');
    this.magazineService.startAddMagazine().subscribe(
      res => {
        location.replace('http://localhost:4200/tasks');
        

      }
    );
  }

}
