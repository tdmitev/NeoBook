import { Component, inject, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Student } from '../dtos/student';
import { RouterModule } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { AuthService } from '../auth/keycloak.service';
import {MatDialog, MatDialogModule} from '@angular/material/dialog';
import { GradeDialogComponent } from '../grade-dialog/grade-dialog.component';
import { from, Observable } from 'rxjs';


@Component({
  selector: 'app-student-list',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    MatCardModule,
    MatDialogModule
  ],
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.scss']
})
export class StudentListComponent {
  @Input() students: Student[] = [];

  private readonly authService = inject(AuthService);

  readonly dialog = inject(MatDialog);

  get sortedStudents() {
    return [...this.students].sort((a, b) => a.name.localeCompare(b.name));
  }

  isCurrentUserTeacher(): Observable<boolean> {
    return from(this.authService.getUserRoles()
      .then(roles => roles.some(role => role === 'teacher')));
  }

  openDialog(studentId: number, event: MouseEvent): void {
    event.stopPropagation();
    this.dialog.open(GradeDialogComponent, {
      data: { studentId, subjectId: 1 },
    });
  }
}