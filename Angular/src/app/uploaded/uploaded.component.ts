import { FormGroup } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
@Component({
  selector: 'app-uploaded',
  templateUrl: './uploaded.component.html',
  styleUrls: ['./uploaded.component.css']
})

export class UploadedComponent implements OnInit {

 files=new Array<File>();
   constructor(private _auth:AuthService) { }
 
   ngOnInit(): void {
   }
  onSelectFile(event){
    const file=event.target.files[0];
    this.files.push(file);
    console.log(file);
  }
registerUser(){
  const formData=new FormData();
  for(var i=0;i<this.files.length;i++)
  {
    var str='file' + i;
    formData.append("file",this.files[i]);
  }
 this._auth.registerUser(formData).subscribe(
   res=>{
     console.log(res);
   },
   err=>console.log(err)
 )
}

}
