import { Component, OnInit } from '@angular/core';
import { TaskService } from 'src/app/service/task.service';
import { FormGroup, FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-activate-user',
  templateUrl: './activate-user.component.html',
  styleUrls: ['./activate-user.component.css']
})
export class ActivateUserComponent implements OnInit {
  processId : string;
  formFields = [];
  inputData: FormGroup;
  taskId : string; 

  constructor(private router: Router, private taskService : TaskService, private route:ActivatedRoute, private fb : FormBuilder, private userService: UserService) {
    
   }

  ngOnInit() {
    this.inputData = this.fb.group({
      code : ['']
    });
    this.route.params.subscribe(
      params => {
        this.processId = String(params['id']);
        this.getTaskForm();
      }
    )
  }

  getTaskForm(){
    this.taskService.getActivateTask(this.processId).subscribe(
      res => {
        this.formFields = res.formFields;
        this.taskId = res.taskId;
   //     alert(this.taskId);
      },
      err => {
        alert("Error occured!");
      }
    );
  }

  onSubmit(){
 //   alert(JSON.stringify(this.inputData.value));
    //pozovemo funkciju activate user
    this.userService.activateUser(this.inputData.value, this.taskId).subscribe(
      res=> {
        alert("Successfull activate your acount!");
        this.router.navigate(['/login']);
      }, 
      err => {
        alert("Wrong hash code.");
      }
    )
  }

}
