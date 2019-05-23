import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-skill-form',
  templateUrl: './skill-form.component.html',
  styleUrls: ['./skill-form.component.css']
})
export class SkillFormComponent implements OnInit {

  constructor(private http: HttpClient, private router: Router) { }

  addSkill(event: any){
    if(event.target.nameInput.value!=""){
      this.http.post('http://localhost:8080/add/skill', {name: event.target.nameInput.value})
      .subscribe((response)=>{
        console.log(response);
        this.router.navigate(['/skills']);  
      }, (error) => { this.handleTheError(error);})   
    }else{
      alert("Name field must not be empty.")
    }
  }

  handleTheError(error) {
    alert("Skill with the same name already exists.");
  }

  cancel(){
    this.router.navigate(['/skills']);    
  }

  ngOnInit() {
  }

}
