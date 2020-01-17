import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TaskService } from '../service/task.service';
import { FormGroup, FormBuilder } from '@angular/forms';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-accept-reviewer',
  templateUrl: './accept-reviewer.component.html',
  styleUrls: ['./accept-reviewer.component.css']
})
export class AcceptReviewerComponent implements OnInit {
  id : string;
  formFields = [];
  regUser;
  acceptReviewer: FormGroup;
  scientifics = "";
  constructor(private formBuilder: FormBuilder, private userService: UserService,private route : ActivatedRoute, private router: Router, private taskService : TaskService) { }

  ngOnInit() {
    this.acceptReviewer = this.formBuilder.group({
      status : ['false']
    });
    this.route.params.subscribe(
      params =>{
        this.id = String(params['id']);
        this.getTaskForm();
        this.getRegUser();
      }
    )
  }

  getTaskForm(){
    //  alert(this.id);
      this.taskService.getTaskForm(this.id).subscribe(
        res => {
          this.formFields = res.formFields;
        }, 
        err => {
          alert("Error occured");
        }
        
      );
  }

  getRegUser(){
   // alert(this.id);
    this.taskService.getRegUser(this.id).subscribe(
      res=> {
        this.regUser = res;
     //   alert(JSON.stringify(this.regUser));
        this.scientifics = this.regUser.scientific.toString();
      }, 
      err => {
        alert("Error occures - reg user");
      }
    )
  }

  onSubmit(){
   // alert("Uneseni podaci ");
 //   alert(JSON.stringify(this.acceptReviewer.value));
    this.userService.acceptReviewer(this.acceptReviewer.value, this.id).subscribe(
      res => {
        alert("Success accept new status!");
        this.router.navigate(['tasks']);
      },
      err => {
        alert("Unsuccess accept new status!");
      }
    )
  }
}
