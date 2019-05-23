import { Component, OnInit, Input } from '@angular/core';
import { Candidate } from './candidate.model';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-candidates',
  templateUrl: './candidates.component.html',
  styleUrls: ['./candidates.component.css']
})
export class CandidatesComponent implements OnInit {

  candidates: any;
  skills: any;
  isActive: boolean = false;
  filteredSkills: any = [];

  constructor(private http: HttpClient, private router: Router) {}

  filterList(){
    this.isActive = !this.isActive
  }

  filter(event: any){
    this.http.post('http://localhost:8080/search', {skills: this.filteredSkills})
    .subscribe((response)=>{
      this.filteredSkills = [];
      this.candidates = response;
      for (let index = 0; index < this.candidates.length; index++) {
        this.candidates[index].dateOfBirth=this.candidates[index].dateOfBirth.slice(0, 10);
      }
      this.isActive = !this.isActive
      console.log(response); 
    })
  }

  changeStatus(skillId: number,  event: any){
    if(event.target.checked){
      console.log("ID:" + skillId);
      this.filteredSkills.push(skillId);
    }else{
      console.log("IDB:" + skillId);
      for (let index = 0; index < this.filteredSkills.length; index++) {
        if(this.filteredSkills[index]===skillId)
          this.filteredSkills.splice(index, 1);
      }
    }
    console.log(this.filteredSkills);
  }

  search(event: any){
    this.http.post('http://localhost:8080/search', {fullName: event.target.searchInput.value})
    .subscribe((response)=>{
      console.log(response); 
      this.candidates = response;
      for (let index = 0; index < this.candidates.length; index++) {
        this.candidates[index].dateOfBirth=this.candidates[index].dateOfBirth.slice(0, 10);
      }
    })
  }

  addSkillToCandidate(candidateId: string){
    this.router.navigate(['/addSkill/candidate/' + candidateId]);    
  }

  goToCandidateForm(){
    this.router.navigate(['/add/candidate']);    
  }
  
  goToEditCandidate(candidateId: string){
    this.router.navigate(['/update/candidate/' + candidateId]);
  }

  removeCandidate(candidateId: string){
    this.http.delete('http://localhost:8080/delete/candidate/' + candidateId)
    .subscribe((response)=>{
      console.log(response);
      this.ngOnInit();  
    }) 
  }

  removeSkill(candidateId: string, skillId: string){ ///delete/candidateSkill/{skillId}/{candidateId}
    this.http.delete('http://localhost:8080/delete/candidateSkill/' + skillId +"/"+ candidateId)
    .subscribe((response)=>{
      console.log(response);
      this.ngOnInit();
    });
  }

  ngOnInit() {
    this.http.get('http://localhost:8080/candidates')
    .subscribe((response) => {  
      this.candidates = response;
      for (let index = 0; index < this.candidates.length; index++) {
        this.candidates[index].dateOfBirth=this.candidates[index].dateOfBirth.slice(0, 10);
      }
      console.log(this.candidates);
    });
   

    this.http.get('http://localhost:8080/skills')
    .subscribe((response) => {  
      this.skills = response;
      console.log(this.skills);
    });

  }

}
