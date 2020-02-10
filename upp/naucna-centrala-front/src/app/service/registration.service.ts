import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, VirtualTimeScheduler } from 'rxjs';
import { UserRegistration } from '../user/model';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  constructor(private httpClient: HttpClient) { }

  runRegistration(){
    return this.httpClient.get('/api/user/runRegistration') as Observable<any>
  }


  getAllScientificFields(){
    return this.httpClient.get("/api/scientific/get") as Observable<any>;
  }

  submit(user, taskId){
    return this.httpClient.post('/api/user/submit/'.concat(taskId), user) as Observable<any>;
  }
}
