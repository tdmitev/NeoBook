import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Student } from '../dtos/student';
import { RouterModule } from '@angular/router';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-student-list',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    MatCardModule
  ],
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.scss']
})
export class StudentListComponent {
  @Input() students: Student[] = [];

  get sortedStudents() {
    return [...this.students].sort((a, b) => a.name.localeCompare(b.name));
  }
}