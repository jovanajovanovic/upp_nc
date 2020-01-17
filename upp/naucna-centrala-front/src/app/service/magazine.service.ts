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

  getInputMagazine(taskId){
    //OVA FUNKCIJA VRACA PODATKE O MAGAZINU KOJI SE UNOSI 
    return this.httpClient.get<any>("/api/getMagazine/".concat(taskId));
  }

  getEditors(scientific){
    //vracamo sve editore koji su za izabrane oblasti 
    return this.httpClient.post<any>("/api/getEditors", scientific);
  }
  getReviewers(scientific){
    //vracamo sve editore koji su za izabrane oblasti 
    return this.httpClient.post<any>("/api/getReviewers", scientific);
  }

  addBoard(dto, taskId){
    return this.httpClient.post<any>("/api/addEditBoard/".concat(taskId), dto, {headers: this.headers});
  }

  checkData(ok, taskId){
    return this.httpClient.post<any>("/api/checkData/".concat(taskId), ok, {headers : this.headers});
  }

  activateMagazine(status, taskId){
    return this.httpClient.post<any>("/api/activateMagazine/".concat(taskId), status, {headers : this.headers});

  }

  getAll(){
    return this.httpClient.get<any>("/api/allMagazines");
  }
}
