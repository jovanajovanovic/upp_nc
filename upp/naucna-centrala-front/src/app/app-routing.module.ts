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
import { InputEditorAndReviewerComponent } from './magazine/input-editor-and-reviewer/input-editor-and-reviewer.component';
import { CheckDataComponent } from './magazine/check-data/check-data.component';
import { ActivateMagazineComponent } from './magazine/activate-magazine/activate-magazine.component';
import { ViewMagazinesComponent } from './magazine/view-magazines/view-magazines.component';
import { ChangeInputDataComponent } from './magazine/change-input-data/change-input-data.component';


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
  path : 'verification/:id', 
  component : AcceptReviewerComponent
}, 
{
  path : 'activate/:id', 
  component : ActivateUserComponent
},
{
  path : 'addMagazine', 
  component : RegisterMagazineComponent
},
{
  path: 'add/:id',
  component: InputEditorAndReviewerComponent
},
{
  path : 'check/:id',
  component : CheckDataComponent
},
{
  path: 'change/:id',
  component: ActivateMagazineComponent
},
{
  path: 'magazines', 
  component: ViewMagazinesComponent
},
{
  path: 'input/:id',
  component: ChangeInputDataComponent
}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
