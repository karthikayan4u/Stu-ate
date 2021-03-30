import { Component, OnInit } from '@angular/core';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';



export interface PeriodicElement {
  name: string;
  position: number;
  email: string;
  symbol: string;
}

const ELEMENT_DATA: PeriodicElement[] = [
  {position: 1, name: 'qwer', email: 'abcd', symbol: ''},
  {position: 2, name: 'qwer', email: 'abcd', symbol: ''},
  {position: 3, name: 'qwer', email: 'abcd', symbol: ''},
  {position: 4, name: 'qwer', email: 'abcd', symbol: ''},
  {position: 5, name: 'poiu', email: 'efgh', symbol: ''},
  {position: 6, name: 'poiu', email: 'efgh', symbol: ''},
  {position: 7, name: 'poiu', email: 'efgh', symbol: ''},
  {position: 8, name: 'poiu', email: 'efgh', symbol: ''},
  {position: 9, name: 'tyur', email: 'hijk', symbol: ''},
  {position: 10, name: 'tyur', email: 'hijk', symbol: ''},
  {position: 11, name: 'qwer', email: 'abcd', symbol: ''},
  {position: 12, name: 'qwer', email: 'abcd', symbol: ''},
  {position: 13, name: 'qwer', email: 'abcd', symbol: ''},
  {position: 14, name: 'qwer', email: 'abcd', symbol: ''},
  {position: 15, name: 'poiu', email: 'efgh', symbol: ''},
  {position: 16, name: 'poiu', email: 'efgh', symbol: ''},
  {position: 17, name: 'poiu', email: 'efgh', symbol: ''},
  {position: 18, name: 'poiu', email: 'efgh', symbol: ''},
  {position: 19, name: 'tyur', email: 'hijk', symbol: ''},
  {position: 20, name: 'tyur', email: 'hijk', symbol: ''},
];

@Component({
  selector: 'app-admindashboard',
  templateUrl: './admindashboard.component.html',
  styleUrls: ['./admindashboard.component.css']
})
export class AdmindashboardComponent implements OnInit {
  displayedColumns: string[] = ['position', 'name', 'email', 'symbol'];
  dataSource = new MatTableDataSource(ELEMENT_DATA);
  
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  constructor() { }

  ngOnInit(): void {
  }

}
