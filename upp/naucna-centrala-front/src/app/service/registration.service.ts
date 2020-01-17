import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, VirtualTimeScheduler } from 'rxjs';
import { UserRegistration } from '../user/model';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  constructor(private httpClient: HttpClient) { }

  getRegistrationForm(){
    return this.httpClient.get('/api/user/getRegistrationForm') as Observable<any>
  }


  getAllScientificFields(){
    return this.httpClient.get("/api/scientific/get") as Observable<any>;
  }

  register(user, taskId){
    return this.httpClient.post('/api/user/register/'.concat(taskId), user) as Observable<any>;
  }
}
