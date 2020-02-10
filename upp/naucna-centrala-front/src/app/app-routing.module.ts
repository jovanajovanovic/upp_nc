import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MainComponent } from './main/main.component';
import { LoginComponent } from './user/login/login.component';
import { ViewTaskComponent } from './view-task/view-task.component';
import { RoleGuardService } from './service/role-guard.service';
import { ViewMagazinesComponent } from './magazine/view-magazines/view-magazines.component';
import { TaskComponent } from './task/task.component';
import { ShowMagazineComponent } from './magazine/show-magazine/show-magazine.component';


const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'tasks',
    component: ViewTaskComponent
  },
  {
    path: 'task/:id',
    component: TaskComponent
  },
  {
    path: 'magazines',
    component: ViewMagazinesComponent
  }, 
  {
    path: 'magazine/:id',
    component: ShowMagazineComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
