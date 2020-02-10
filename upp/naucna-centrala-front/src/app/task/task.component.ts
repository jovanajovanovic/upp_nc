import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { TaskService } from '../service/task.service';
import { ActivatedRoute, Router } from '@angular/router';
import { RegistrationService } from '../service/registration.service';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {
  private formFieldsDto = null;
  private formFields = [];
  private processInstance = '';
  private enumValues = [];
  private regUserForm: FormGroup;
  private selectScientific;
  private scientificFields = [];
  private submitted = false;
  private processId = '';
  private object;
  private editors = [];
  private reviewers = [];
  constructor(private router: Router, private route: ActivatedRoute, private registrationService: RegistrationService,
    private formBuilder: FormBuilder, private taskService: TaskService) {
    this.route.params.subscribe(
      params => {
        this.processId = String(params['id']);
      }
    );
     let x = taskService.getTaskForm(this.processId);

    x.subscribe(
      res => {
        this.formFieldsDto = res;
        this.formFields = res.formFields;
        this.processInstance = res.processInstanceId;
        this.regUserForm = this.formBuilder.group({});
        this.object = res.readonlyFields;
        alert(JSON.stringify(this.object));
        this.formFields.forEach((field) => {
          //  alert(JSON.stringify(field.validationConstraints));
          //      const valid = [];
          //    field.validationConstraints.forEach(element => {
          //    alert(JSON.stringify(element));
          //      let val = element.name;
          //    alert(Validators[val]);
          //    valid.push(Validators[val]);
          //    });
          // const control = this.formBuilder.control(field.id);
          // drugi parametar u form control bi trebao da bude validator
          this.regUserForm.addControl(field.id, new FormControl(field.value.value, []));

          if (field.type.name === 'multiselect'  ) {
            this.scientificFields = Object.keys(field.type.values);
          }
          if (field.type.name === 'multiselectE' ) {
            this.editors = Object.keys(field.type.values);
          }
          if (field.type.name === 'multiselectR' ) {
            this.reviewers = Object.keys(field.type.values);
          }

          if (field.type.name === 'enum') {
            this.enumValues = Object.keys(field.type.values);
          }
        });
      },
      err => {
        console.log("Error occured");
      }
    )

  }

  ngOnInit() {

  }

  onSubmit() {
    alert(JSON.stringify(this.regUserForm.value));
    let o = new Array();
    for (var p in this.regUserForm.value) {
      o.push({ name: p, value: this.regUserForm.value[p] });
      // alert(this.regUserForm.value[p]);
    }

    let res = this.registrationService.submit(o, this.formFieldsDto.taskId);
    res.subscribe(
      res => {
        console.log(res);
        alert("Successfully submit"); //ovde cemo otici na stranicu za unos hash-coda, koji je korisnik dobio u poruci
        this.router.navigate(['/tasks']);
      },
      err => {
        alert("Unsuccessfully submited!");

      }
    )
  }

  uploadFile(files: File[]){
    alert("Upload fajla " + files);
  //  let fileList: FileList = this.regUserForm.value.pdf;
  //  alert(fileList);
    let file = files[0];
    let formData = new FormData();
    formData.append('file', file, file.name);

    this.taskService.uploadFile(this.processId, formData).subscribe(
      data => {

      }, 
      err => {
        console.log(err.error);

      }
    );
  }

  downloadFile(){
    var article = '';
    for (let ob of this.object){
      if (ob.name == 'Title'){
        article = ob.value;
      }
    }
    alert("Download " + JSON.stringify(this.object));
    this.taskService.downloadFile(article).subscribe(
      (data: Blob) => {
        var file = new Blob([data], {type: 'applicattion/pdf'});
        var fileUrl = URL.createObjectURL(file);

        window.open(fileUrl);
    }, error=>{
        console.log('ERROR DOWNLOADING THE FILE');
    }
    );
  }

}
