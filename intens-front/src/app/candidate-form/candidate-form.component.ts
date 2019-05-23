import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-candidate-form',
  templateUrl: './candidate-form.component.html',
  styleUrls: ['./candidate-form.component.css']
})
export class CandidateFormComponent implements OnInit {

  constructor(private http: HttpClient, private router: Router) { }

  addCandidate(event: any){
    if(event.target.fullNameInput.value!="" &&  event.target.dateOfBirthInput.value!="" && event.target.phoneInput.value!="" && event.target.emailInput.value!=""){
      if(this.validateEmail(event.target.emailInput.value)){
      this.http.post('http://localhost:8080/add/candidate', {fullName: event.target.fullNameInput.value, 
        dateOfBirth: event.target.dateOfBirthInput.value, telNumber: event.target.phoneInput.value, 
        email: event.target.emailInput.value})
      .subscribe((response)=>{
        console.log(response);
        this.router.navigate(['/candidates']);  
      }, (error) => { this.handleTheError(error); } ) 
    }else{
      alert("Please enter a valid email. (example@mail.com)")
    }
    }else{
      alert("All fields must be correctly filled.")
    }  
  }

  validateEmail(email) {
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
  }

  handleTheError(error) {
    alert("Candidate with the same email already exists.");
  }

  cancelCandidate(){
    this.router.navigate(['/candidates']);    
  }

  ngOnInit() {
  }

}
