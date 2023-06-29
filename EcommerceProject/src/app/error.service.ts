import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ErrorService {

  constructor() { }

  errorsMsg = {
    UNKNOWN: "An Unknown Error is Occured",
    EMAIL_EXISTS: "This Email is already exist. Please try with another email",
  
  }
}
