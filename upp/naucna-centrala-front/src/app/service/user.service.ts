import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private headers = new HttpHeaders({'Content-Type' : 'application/json'});
  constructor(private httpClient : HttpClient) { }

  login(login_dto){
    return this.httpClient.post("api/login", login_dto, {headers: this.headers, responseType: 'json', withCredentials: true});
  }


  activateUser(dto, taskId){
    return this.httpClient.post("api/user/activateUser/".concat(taskId), dto, {headers:this.headers, responseType: "json"});
  }
  
  acceptReviewer(dto, taskId){
    return this.httpClient.post("api/user/acceptReviewer/".concat(taskId), dto, {headers:this.headers, responseType: "json"});
  }

  isLoggedIn() : boolean {
    const token = localStorage.getItem('user');
    if (!token){
      return false;
    }
    return true;
  }

}
