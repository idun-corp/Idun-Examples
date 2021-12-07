import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MsalService } from '@azure/msal-angular';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  public response: any;

  constructor(
    private http: HttpClient,
    private msalService: MsalService,
  ) {
  }

  public ngOnInit() {
    this.msalService.handleRedirectObservable()
      .subscribe(() => {
        this.http.get<any>(`${environment.proptechOsApiUrl}/person/me`, {
          headers: {
            "Content-Type": "application/json",
            "X-Requested-With": "XMLHttpRequest"
          },
          observe: "response",
          responseType: "json"
        })
        .subscribe(response => this.response = response.body)
      })
  }
}
