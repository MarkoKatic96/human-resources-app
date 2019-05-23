import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-skill-list',
  templateUrl: './skill-list.component.html',
  styleUrls: ['./skill-list.component.css']
})
export class SkillListComponent implements OnInit {

  skills: any;
  candidate: any;

  constructor(private route: ActivatedRoute, private http: HttpClient, private router: Router) { }

  addNonAquiredSkill(skillId: string){ 
    this.http.get('http://localhost:8080/add/candidateSkill/' + skillId + "/" + this.route.snapshot.paramMap.get("candidateId"))
    .subscribe((response)=>{
      console.log(response);
      this.ngOnInit(); 
    })   
  }

  ngOnInit() {
    this.http.get('http://localhost:8080/notAquiredSkills/' + this.route.snapshot.paramMap.get("candidateId"))
    .subscribe((response)=>{
      this.skills=response;
      console.log(this.skills);
    })

    this.http.get('http://localhost:8080/candidates/' + this.route.snapshot.paramMap.get("candidateId"))
    .subscribe((response)=>{
      this.candidate=response;
      console.log(this.skills);
    })

  }

}
