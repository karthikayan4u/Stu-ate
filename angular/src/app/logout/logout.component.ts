import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LogoutService } from './logout.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor(private logoutService: LogoutService,
    private router: Router) { }

  ngOnInit(): void {
    this.deleteSession();
  }

  public deleteSession(): void {
    this.logoutService.deleteSession().subscribe(
      (response: void) => {
        console.log(response);
        this.router.navigate(['/login']);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

}
