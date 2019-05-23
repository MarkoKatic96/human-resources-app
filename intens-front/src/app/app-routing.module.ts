import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SkillsComponent } from './skills/skills.component';
import { CandidatesComponent } from './candidates/candidates.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { SkillFormComponent } from './skill-form/skill-form.component';
import { CandidateFormComponent } from './candidate-form/candidate-form.component';
import { CandidateEditComponent } from './candidate-edit/candidate-edit.component';
import { SkillListComponent } from './skill-list/skill-list.component';

const routes: Routes = [
  {path: '', redirectTo: '/candidates', pathMatch: 'full'},
  {path: 'candidates', component: CandidatesComponent},
  {path: 'skills', component: SkillsComponent},
  {path: 'add/skill', component: SkillFormComponent},
  {path: 'add/candidate', component: CandidateFormComponent},
  {path: 'update/candidate/:candidateId', component: CandidateEditComponent},
  {path: 'addSkill/candidate/:candidateId', component: SkillListComponent},
  {path: '**', component: PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
