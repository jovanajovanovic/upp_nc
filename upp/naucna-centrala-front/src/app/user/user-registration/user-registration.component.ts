import { Component, OnInit } from '@angular/core';
import { RegistrationService } from 'src/app/service/registration.service';
import { UserRegistration } from '../model';
import { FormBuilder, FormGroup, Validators } from  '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-registration',
  templateUrl: './user-registration.component.html',
  styleUrls: ['./user-registration.component.css']
})
export class UserRegistrationComponent implements OnInit {
  private formFieldsDto = null;
  private formFields = [];
  private processInstance = '';
  private enumValues = [];
  private regUserForm : FormGroup;
  private selectScientific; 
  private scientificFields = [];

  constructor(private registrationService : RegistrationService, private formBuilder: FormBuilder, private router: Router) { 
    let x = registrationService.getRegistrationForm();

    x.subscribe(
      res => {
        this.formFieldsDto = res;
        this.formFields = res.formFields;
        this.processInstance  = res.processInstanceId;
        alert(this.processInstance);
      },
      err => {
        console.log("Error occured");
      }
    )

    let s = registrationService.getAllScientificFields();
    s.subscribe(
      res => {
        this.scientificFields = res;
      }, 
      err => {
        console.log("Error occured - get scientific");
      }
    )
  }

  ngOnInit() {
    this.regUserForm = this.formBuilder.group({
      name : [''], 
      surname : [''], 
      city : [''],
      country : [''],
      email : [''], 
      password : [''],
      username : [''],
      metier : [''],
      scientific : [[]],
      reviewer : ['false']
    });
  }

  get formControls() {return this.regUserForm.controls}



  onSubmit(){
    alert("Uneti podaci");
    let o = new Array();
    let value = this.regUserForm.value;
    alert(JSON.stringify(value));
    for (var property in value) {
      o.push({fieldId : property, fieldValue : value[property]});
    }
    let res = this.registrationService.register(this.regUserForm.value, this.formFieldsDto.taskId);
    res.subscribe(
      res =>{ console.log(res);
      alert("Successfully registered"); //ovde cemo otici na stranicu za unos hash-coda, koji je korisnik dobio u poruci
      this.router.navigate(['/activate/' + this.processInstance]);
      }, 
      err=> {
        alert("Unsuccessfully registered!");
        this.router.navigate(['registration']);
      }
      )

  }

}
