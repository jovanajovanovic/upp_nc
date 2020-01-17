import { Component, OnInit } from '@angular/core';
import { MagazineService } from 'src/app/service/magazine.service';
import { RegistrationService } from 'src/app/service/registration.service';
import { FormGroup, FormBuilder } from '@angular/forms';
import { RouterLink, Router } from '@angular/router';

@Component({
  selector: 'app-register-magazine',
  templateUrl: './register-magazine.component.html',
  styleUrls: ['./register-magazine.component.css']
})
export class RegisterMagazineComponent implements OnInit {

  private formFieldsDto = null;
  private formFields = [];
  private processInstance = "";
  private enumValues= [];
  private scientificFields = [];
  private magazineForm : FormGroup;
  private taskId = ""
  constructor(private magazineService: MagazineService,private router: Router, private scientificService : RegistrationService, private fb : FormBuilder) {
 
    this.magazineService.startAddMagazine().subscribe(
      res => {
        this.formFields = res.formFields;
   //     this.formFieldsDto = res.formFields;
        this.processInstance = res.processInstance;
        this.taskId = res.taskId;

        this.formFields.forEach( (field) =>{
          
          if( field.type.name=='enum'){
            this.enumValues = Object.keys(field.type.values);
          }
        });
      },
      err => {
        alert("Error occured - get magazine form");
      }
    );

    scientificService.getAllScientificFields().subscribe(
      res => {
        this.scientificFields = res;
      }, 
      err => {
        console.log("Error occured - get scientific");
      }
    );

   }

  ngOnInit() {
    this.magazineForm = this.fb.group({
      name : [''], 
      issn : [''], 
      scientific : [''],
      payment : ['']
    });
  }


  onSubmit(){
  //  alert(JSON.stringify(this.magazineForm.value));
    this.magazineService.saveMagazine(this.magazineForm.value, this.taskId).subscribe(
      res => {
        alert("Success add magazine!");
        //sad cemo da prikazemo sledeci task
        
        this.router.navigate(['tasks']);
      }, 
      err=> {
        alert('Unsccess add magazine');
      }
    )
  }

}
