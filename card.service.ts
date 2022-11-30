import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { Card } from 'src/app/shared/model/card';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AddCardService {
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  constructor(private http:HttpClient) { }

  addcard(customerEmailId: any,card:Card):Observable<string>{
    let url=environment.cardAPIUrl+"/customer/"+customerEmailId+"/cards";
    console.log(url)
    return this.http.post<string>(url,card,{ headers: this.headers, responseType: 'text' as 'json' });

  }
  
  private handleError(err: HttpErrorResponse) {
    console.log(err)
    let errMsg: string = '';
    if (err.error instanceof Error) {
        errMsg = err.error.message;
        console.log(errMsg)
    }
    else if (typeof err.error === 'string') {
        errMsg = JSON.parse(err.error).errorMessage
    }
    else {
        if (err.status == 0) {
            errMsg = "A connection to back end can not be established.";
        } else {
            errMsg = err.error.message;
        }
    }
    return throwError(errMsg);
}


  
}
