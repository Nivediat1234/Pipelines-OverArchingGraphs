import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class AuthService {
 private_registerUrl="http://localhost:8044";
 constructor(private http:HttpClient){}
 registerUser(formData:FormData){
   return this.http.post(this.private_registerUrl,formData,{responseType:'text'});
 }
 downloadFile(data){
  const REQUEST_PARAMS =new HttpParams().set('fileName',data.fileName);
  const REQUEST_URI="http://localhost:8044/home"
  return this.http.get(REQUEST_URI,{
    params:REQUEST_PARAMS,
    responseType:'arraybuffer'
  })
  }


}
