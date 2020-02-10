import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MagazineService {
  [x: string]: any;
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  constructor(private httpClient: HttpClient) { }

  startAddMagazine() {
    return this.httpClient.get<any>('/api/runAddMagazine');
  }
  getMagazine(issn) {
  // OVA FUNKCIJA VRACA PODATKE O MAGAZINU
    return this.httpClient.get<any>('/api/getMagazine/'.concat(issn));
  }

  getAll() {
    return this.httpClient.get<any>('/api/allMagazines');
  }

  startAddArticle() {
    return this.httpClient.get<any>('/api/runAddArticle');
  }
}
