import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MagazineService } from 'src/app/service/magazine.service';
import { RegistrationService } from 'src/app/service/registration.service';
import { FormBuilder, Form, FormGroup } from '@angular/forms';
import { TaskService } from 'src/app/service/task.service';

@Component({
  selector: 'app-change-input-data',
  templateUrl: './change-input-data.component.html',
  styleUrls: ['./change-input-data.component.css']
})
export class ChangeInputDataComponent implements OnInit {
  private formFields = [];
  magazineForm: FormGroup;
  magazine; 
  private taskId: string = "";
  scientificFields;
  enumValues;
  constructor(private route: ActivatedRoute,private taskService: TaskService, private router: Router, private magazineService: MagazineService, private scientificService: RegistrationService, private fb: FormBuilder) { 
    
  }

  ngOnInit() {
      //uzmemo id
      this.route.params.subscribe(
        params =>{
          this.taskId = String(params['id']);
          this.getTaskForm();
          this.getMagazine();
        }
      )
      this.scientificService.getAllScientificFields().subscribe(
        res => {
          this.scientificFields = res;
        }, 
        err => {
          console.log("Error occured - get scientific");
        }
      );

      this.magazineForm = this.fb.group({
        name : [], 
        issn : [],
        scientific : [],
        payment: []
      })
        
    }

    getTaskForm(){
      this.taskService.getTaskForm(this.taskId).subscribe(
        res=> {
          this.formFields = res.formFields;
          this.formFields.forEach( (field) =>{
          
            if( field.type.name=='enum'){
              this.enumValues = Object.keys(field.type.values);
            }
          });
        },
        err => {
          alert("Error occured!");
        }
      )
    }
  
    getMagazine(){
      this.magazineService.getInputMagazine(this.taskId).subscribe(
        data => {
          this.magazine = data;
          this.magazineForm.value['name'] = this.magazine.name;
          this.magazineForm.value['issn'] = this.magazine.issn;
          this.magazineForm.value['scientific'] = this.magazine.scientific;
          this.magazineForm.value['payment'] = this.magazine.payment;
          
        },
        err => {
          alert("Error occured - get magazine");
        }
      )
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
