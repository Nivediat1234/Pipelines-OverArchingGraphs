import { HttpClient, HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { AuthService } from '../auth.service';
import {saveAs} from 'file-saver';
const MIME_TYPES={
  pdf:'application/pdf',
  yaml:'application/yaml',
  yml:'application/yml'
}
@Component({
  selector: 'app-merge',
  templateUrl: './merge.component.html',
  styleUrls: ['./merge.component.css']
})
export class MergeComponent implements OnInit {
fileName="application.yml";
  constructor(private _auth:AuthService) { }

  ngOnInit(): void {
  
 

  }
downloadFile(){
  const EXT=this.fileName.substr(this.fileName.lastIndexOf('.')+1);
  this._auth.downloadFile({'fileName':this.fileName})
  .subscribe(data=>{
saveAs(new Blob([data]),this.fileName);
  })
}
}
