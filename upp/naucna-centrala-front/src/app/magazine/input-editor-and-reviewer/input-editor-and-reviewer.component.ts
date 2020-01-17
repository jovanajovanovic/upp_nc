import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { TaskService } from 'src/app/service/task.service';
import { MagazineService } from 'src/app/service/magazine.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-input-editor-and-reviewer',
  templateUrl: './input-editor-and-reviewer.component.html',
  styleUrls: ['./input-editor-and-reviewer.component.css']
})
export class InputEditorAndReviewerComponent implements OnInit {

  private magazine; 
  id: string; 
  formFields = [];
  inputEditorBoard : FormGroup;
  editors;
  reviewers;
  editorList = [];
  reviewerList = [];

  constructor(private router: Router,private route: ActivatedRoute, private fb : FormBuilder, private taskService : TaskService, private magazineService: MagazineService) { }

  ngOnInit() {
    //dobavim id taska
    this.route.params.subscribe(
      params => {
        this.id = String(params['id']);
        this.getTaskForm();
        this.getMagazine();
      }
    )
    this.inputEditorBoard = this.fb.group({
      editors : [''],
      reviewers : ['']
    });

  }
    getTaskForm(){
      this.taskService.getTaskForm(this.id).subscribe(
        res => {
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
          this.getEditors();
          this.getReviewers();
          if(this.magazine.editorList != undefined){
            this.editorList = this.magazine.editorList;
          }
          if(this.magazine.reviewerList != undefined){
            this.reviewerList = this.magazine.reviewerList;
          }
        },
        err => {
          alert("Error occured = get magazine!");
        }
      )
    }

    getEditors(){
   //   alert(this.magazine.scientific);
      this.magazineService.getEditors(this.magazine.scientific).subscribe
      (
        res => {
          //prodjemo kroz listu editora 
          this.editors = res;
      //    alert(JSON.stringify(res));
          
        },
        err => {
          alert("Error occured - get editors");
        }
      )
    }

    
    getReviewers(){
      this.magazineService.getReviewers(this.magazine.scientific).subscribe
      (
        res => {
          //prodjemo kroz listu editora 
          this.reviewers = res;
      //    alert(JSON.stringify(res));
          
        },
        err => {
          alert("Error occured - get reviewers");
        }
      )
    }

    onSubmit(){
 //     alert(JSON.stringify(this.inputEditorBoard.value));
      this.magazineService.addBoard(this.inputEditorBoard.value, this.id).subscribe(
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
