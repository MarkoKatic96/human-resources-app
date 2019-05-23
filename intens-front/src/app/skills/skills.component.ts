import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-skills',
  templateUrl: './skills.component.html',
  styleUrls: ['./skills.component.css']
})
export class SkillsComponent implements OnInit {

  skills: any;

  constructor(private http: HttpClient, private router: Router) {

  }

  goToSkillForm(){
    this.router.navigate(['/add/skill']);    
  }

  deleteSkill(skillId: string){
    this.http.delete('http://localhost:8080/delete/skill/' + skillId)
    .subscribe((response)=>{
      console.log(response);
      this.ngOnInit();
    })
  }

  ngOnInit() {
    this.http.get('http://localhost:8080/skills')
    .subscribe((response)=>{
      this.skills=response;
      console.log(this.skills);
    })
  }

}
