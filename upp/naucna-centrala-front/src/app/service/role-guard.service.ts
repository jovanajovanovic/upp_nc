import { Injectable } from '@angular/core';
import { UserService } from './user.service';
import { Router, ActivatedRouteSnapshot } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class RoleGuardService {

  constructor(public auth : UserService, public router: Router) { }

  canActivate(route: ActivatedRouteSnapshot): boolean {
    // this will be passed from the route config
    // on the data property
    let expectedRoles: string = route.data.expectedRoles;
    
    const item = localStorage.getItem('user');    

    let jwt: JwtHelperService = new JwtHelperService();

    if (!item) {
      this.router.navigate(['/login']);
      return false;
    }

    // decode the token to get its payload
    const info = jwt.decodeToken(item);

    // second parametar is limit
    let roles: string[] = expectedRoles.split("|", 5);
    //console.log(roles.indexOf(info.roles));
    //console.log(info.roles);
    
    if (!this.auth.isLoggedIn() || roles.indexOf(info.roles) === -1) {
      this.router.navigate(['']);
      return false;
    }
    
    return true;
  }
}
