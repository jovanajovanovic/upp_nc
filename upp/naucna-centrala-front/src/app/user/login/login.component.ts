import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/service/user.service';
import { FormGroup, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  private loginForm : FormGroup;
  constructor(private formBuilder: FormBuilder, private router: Router, private userService: UserService) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username : [''],
      password : ['']
    });
  }

  get formControls() {return this.loginForm.controls}

  onSubmit(){
    alert("Uneti podaci: ");
    alert(JSON.stringify(this.loginForm.value));
    this.userService.login(this.loginForm.value)
    .subscribe(
      result => {
        localStorage.setItem("user", JSON.stringify(result));
        location.replace("http://localhost:4200/tasks");
        alert("Successful login!");
      },
      error => {
        alert("Wrong username or password!");
      }
    )
  }



}
