import { AbstractControl } from "@angular/forms";


export class ExpiryDateValidator {
  /*method to check dateOfReading is a future date*/
  static checkDate(dateOfReading: AbstractControl): { 'checkDate': true } | null {
    let value = "" + dateOfReading.value;
    let date:Date=new Date();
    let dateofreading:Date=new Date(value);
    if(dateofreading <= date )
    { 
       return { 'checkDate': true };
    }
    return null;
  }
}
