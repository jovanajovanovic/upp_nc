import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { UserService } from '../service/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  logUser : boolean = false;
  constructor(private userService : UserService, private router: Router) { }

  ngOnInit() {
    this.logUser = this.userService.isLoggedIn();
  }


  logOut(){
    localStorage.removeItem("user");
    location.reload();
    this.router.navigate(['']);
  }

}
