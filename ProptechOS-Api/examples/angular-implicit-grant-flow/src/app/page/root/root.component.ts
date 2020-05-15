import { Component, OnInit } from '@angular/core';
import {MsalService} from '@azure/msal-angular';
import {environment as env} from '../../../environments/environment';

@Component({
  selector: 'app-root',
  templateUrl: './root.component.html',
  styleUrls: ['./root.component.scss']
})
export class RootComponent implements OnInit {

  accessToken: string;

  constructor( private _msalService: MsalService
  ) { }


  ngOnInit(): void {
    this._msalService.acquireTokenSilent(env.auth.consentScopes).then(token => {
      this.accessToken = token;
    });
  }

}
