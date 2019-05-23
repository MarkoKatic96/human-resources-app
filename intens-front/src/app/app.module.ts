import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CandidatesComponent } from './candidates/candidates.component';
import { SkillsComponent } from './skills/skills.component';
import { CandidateFormComponent } from './candidate-form/candidate-form.component';
import { SkillFormComponent } from './skill-form/skill-form.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { CandidateEditComponent } from './candidate-edit/candidate-edit.component';
import { SkillListComponent } from './skill-list/skill-list.component';


@NgModule({
  declarations: [
    AppComponent,
    CandidatesComponent,
    SkillsComponent,
    CandidateFormComponent,
    SkillFormComponent,
    PageNotFoundComponent,
    CandidateEditComponent,
    SkillListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgbModule
  ],
  providers: [
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
