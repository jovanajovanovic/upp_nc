import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormBuilder } from '@angular/forms';
import { TaskService } from 'src/app/service/task.service';
import { MagazineService } from 'src/app/service/magazine.service';

@Component({
  selector: 'app-check-data',
  templateUrl: './check-data.component.html',
  styleUrls: ['./check-data.component.css']
})
export class CheckDataComponent implements OnInit {

  private id: string;
  checkForm : FormGroup;
  private magazine;
  formFields = [];
  constructor(private magazineService: MagazineService,private taskService: TaskService,private fb: FormBuilder,private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    //dobijamo id taska
    this.route.params.subscribe(
      params => {
        this.id = String(params['id']);
        this.getTaskForm();
        this.getMagazine();
      }
    )

    this.checkForm = this.fb.group({
      ok : [false]
    })
  }

  getTaskForm(){
    this.taskService.getTaskForm(this.id).subscribe(
      res=> {
        this.formFields = res.formFields;
      },
      err => {
        alert("Error occured!");
      }
    )
  }

  getMagazine(){
    this.magazineService.getInputMagazine(this.id).subscribe(
      data => {
        this.magazine = data;
      },
      err => {
        alert("Error occured - get magazine");
      }
    )
  }

  onSubmit(){
  //  alert(JSON.stringify(this.checkForm.value));
  //  alert(this.checkForm.value['ok']);
    this.magazineService.checkData(this.checkForm.value, this.id).subscribe(
      res => {
        alert("Success adding edit board");
        this.router.navigate(['tasks']);
      },
      err => {
        alert("Unsuccess adding edit board");
      }
    )

  }

}
