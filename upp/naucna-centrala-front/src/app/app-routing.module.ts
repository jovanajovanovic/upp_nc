import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MainComponent } from './main/main.component';
import { UserRegistrationComponent } from './user/user-registration/user-registration.component';
import { LoginComponent } from './user/login/login.component';
import { ViewTaskComponent } from './view-task/view-task.component';
import { AcceptReviewerComponent } from './accept-reviewer/accept-reviewer.component';
import { RoleGuardService } from './service/role-guard.service';
import { ActivateUserComponent } from './user/activate-user/activate-user.component';
import { RegisterMagazineComponent } from './magazine/register-magazine/register-magazine.component';


const routes: Routes = [

{
  path : 'registration', 
  component : UserRegistrationComponent
}, 
{
  path : 'login', 
  component: LoginComponent
},
{
  path : 'tasks', 
  component : ViewTaskComponent
},
{
  path : 'verification reviewer/:id', 
  component : AcceptReviewerComponent
}, 
{
  path : 'activate/:id', 
  component : ActivateUserComponent
},
{
  path : 'addMagazine', 
  component : RegisterMagazineComponent
}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
