import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './user/login/login.component';
import { MainComponent } from './main/main.component';
import { UserRegistrationComponent } from './user/user-registration/user-registration.component';
import { HeaderComponent } from './header/header.component';

import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { ViewTaskComponent } from './view-task/view-task.component';
import { AuthInterceptor } from './interceptor/auth.interceptor';
import { AcceptReviewerComponent } from './accept-reviewer/accept-reviewer.component';
import { ActivateUserComponent } from './user/activate-user/activate-user.component';
import { RegisterMagazineComponent } from './magazine/register-magazine/register-magazine.component';
import { InputEditorAndReviewerComponent } from './magazine/input-editor-and-reviewer/input-editor-and-reviewer.component';
import { CheckDataComponent } from './magazine/check-data/check-data.component';
import { ActivateMagazineComponent } from './magazine/activate-magazine/activate-magazine.component';
import { ViewMagazinesComponent } from './magazine/view-magazines/view-magazines.component';
import { ChangeInputDataComponent } from './magazine/change-input-data/change-input-data.component';



@NgModule({
  declarations: [
    AppComponent,
    UserRegistrationComponent,
    LoginComponent,
    MainComponent,
    HeaderComponent,
    ViewTaskComponent,
    AcceptReviewerComponent,
    ActivateUserComponent,
    RegisterMagazineComponent,
    InputEditorAndReviewerComponent,
    CheckDataComponent,
    ActivateMagazineComponent,
    ViewMagazinesComponent,
    ChangeInputDataComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [ {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
