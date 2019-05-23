import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-candidate-edit',
  templateUrl: './candidate-edit.component.html',
  styleUrls: ['./candidate-edit.component.css']
})
export class CandidateEditComponent implements OnInit {

  candidate: any;

  constructor(private route: ActivatedRoute, private http: HttpClient, private router: Router) { 

  }

  editCandidate(event: any){
    if(event.target.fullNameEdit.value!="" && event.target.dateOfBirthEdit.value!="" &&  event.target.phoneEdit.value!="" && event.target.emailEdit.value!=""){
      this.http.put('http://localhost:8080/update/candidate/' + this.route.snapshot.paramMap.get("candidateId"), {fullName: event.target.fullNameEdit.value,
      dateOfBirth: event.target.dateOfBirthEdit.value, telNumber: event.target.phoneEdit.value, email: event.target.emailEdit.value})
      .subscribe((response)=>{
        console.log(response);
        this.router.navigate(['/candidates']);  
      })
    }else{
      alert("All fields must be correctly filled.");
    }
  }

  cancelCandidateEdit(){
    this.router.navigate(['/candidates']);
  }

  ngOnInit() {
    this.http.get('http://localhost:8080/candidates/' + this.route.snapshot.paramMap.get("candidateId"))
    .subscribe((response) => {
      this.candidate = response;
      (<HTMLInputElement>document.getElementById("fullNameEdit")).value = this.candidate.fullName;  
      (<HTMLInputElement>document.getElementById("dateOfBirthEdit")).value = this.candidate.dateOfBirth;   
      (<HTMLInputElement>document.getElementById("phoneEdit")).value = this.candidate.telNumber;   
      (<HTMLInputElement>document.getElementById("emailEdit")).value = this.candidate.email;   

      console.log(response);
    });


  }

}
