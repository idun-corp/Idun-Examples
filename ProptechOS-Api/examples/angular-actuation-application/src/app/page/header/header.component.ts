import { Component, OnInit } from '@angular/core';
import {MsalService} from '@azure/msal-angular';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  userInfo: any;

  constructor(private msalService: MsalService) { }

  login() {
    this.msalService.loginRedirect();
  }

  logout() {
    this.msalService.logout();
    this.userInfo = null;
  }

  ngOnInit(): void {
    this.userInfo = this.msalService.getAccount();
  }

}
