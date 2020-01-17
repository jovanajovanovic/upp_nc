import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  private headers = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(private httpClient: HttpClient) { }

  getMyTask() : Observable<any[]> {
    return this.httpClient.get<any[]>("api/user/get/tasks", {headers: this.headers, responseType: "json"});
  }

  getTaskForm(id){
    return this.httpClient.get('api/task/getForm/'.concat(id)) as Observable<any>;
  } 

  getRegUser(id){
    return this.httpClient.get('api/task/regUser/'.concat(id)) as Observable<any>;
  }

  getActivateTask(processId) : Observable<any> {
    return this.httpClient.get<any>("api/activateTask/".concat(processId));
  } 
}
