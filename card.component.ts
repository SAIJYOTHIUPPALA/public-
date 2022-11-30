import { HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Card } from 'src/app/shared/model/card';
import { AddCardService } from './card.service';
import { ExpiryDateValidator } from './ExpiryDateValidator';



@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {
addCardForm:FormGroup;
errorMessage: string;
successMessage: any; 
card!:Card;
emailId!:any; 

  constructor(private fb:FormBuilder, private service:AddCardService) {
  
   }

  ngOnInit(): void {
   this.card=new Card;
   this.createForm();
  }
  createForm(){
    this.addCardForm=this.fb.group({
      cardNumber:['',[Validators.required,Validators.pattern("^[0-9]{16}$")]],
      cardType:['',Validators.required],
      nameOnCard:['',Validators.required],
      emailId:['',[Validators.required,Validators.email]],
      expiryDate:['',[Validators.required,ExpiryDateValidator.checkDate]],
      cvv:['',[Validators.required,Validators.pattern("^[0-9]{3}$")]]
    })
  }

  addCard(){
    
    this.successMessage="";
    this.errorMessage="";
    this.card = this.addCardForm.value as Card;
    this.service.addcard(this.emailId,this.card).subscribe(
      response=>{this.successMessage=response},
      error=>{this.errorMessage=error.message}
     
      
    )
    this.addCardForm.reset();
    // if(this.successMessage!=null){
    //   alert(this.successMessage)
    // }else{
    //   alert(this.errorMessage)
    // }
    
  }

  addEmail(email){
    this.emailId=email
  }

}
