import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TaskService } from 'src/app/service/task.service';
import { MagazineService } from 'src/app/service/magazine.service';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-activate-magazine',
  templateUrl: './activate-magazine.component.html',
  styleUrls: ['./activate-magazine.component.css']
})
export class ActivateMagazineComponent implements OnInit {
  private id: string;
  activateForm : FormGroup;
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

    this.activateForm = this.fb.group({
      activate : [false]
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
    this.magazineService.activateMagazine(this.activateForm.value, this.id).subscribe(
      res => {
        alert("Success activate magazine");
        this.router.navigate(['magazines']);
      },
      err => {
        alert("Unsuccess activate magazine");
      }
    )

  }


}
