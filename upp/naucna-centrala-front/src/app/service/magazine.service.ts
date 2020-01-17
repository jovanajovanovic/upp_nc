import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MagazineService {
  private headers = new HttpHeaders({'Content-Type': 'application/json'});
  constructor(private httpClient: HttpClient) { }

  startAddMagazine(){
    return this.httpClient.get<any>("/api/getAddMagazineForm");
  }

  saveMagazine(dto, taskId){
    return this.httpClient.post<any>("/api/saveMagazine/".concat(taskId), dto, {headers: this.headers});
  }
}
