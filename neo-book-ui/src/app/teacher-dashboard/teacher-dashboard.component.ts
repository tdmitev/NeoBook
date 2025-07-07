import { Component, inject, Input, OnInit, signal, WritableSignal } from '@angular/core';
import { Teacher } from '../dtos/teacher';
import { UserService } from '../services/user.service';
import { combineLatest } from 'rxjs';
import { SchoolService } from '../services/school.service';
import { SchoolClass } from '../dtos/school-class';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { StudentListComponent } from '../student-list/student-list.component';
import { Student } from '../dtos/student';

@Component({
  selector: 'app-teacher-dashboard',
  providers: [UserService, SchoolService],
  imports: [
    CommonModule,
    MatCardModule,
    MatListModule,
    MatButtonModule,
    MatIconModule,
    StudentListComponent
  ],
  templateUrl: './teacher-dashboard.component.html',
})
export class TeacherDashboardComponent implements OnInit {

  @Input()
  teacherId!: number;

  teacher: WritableSignal<Teacher | undefined> = signal(undefined);

  schoolClass: WritableSignal<SchoolClass | undefined> = signal(undefined);

  private userService = inject(UserService);
  private schoolService = inject(SchoolService);

  ngOnInit(): void {
    combineLatest([
      this.userService.getTeacherById(this.teacherId),
      this.schoolService.getSchoolClasses()
    ]).subscribe(([teacher, schoolClasses]) => {
      this.teacher.set(teacher);
      if (schoolClasses.length > 0) {
        this.schoolClass.set(schoolClasses[0]);
      }
    });
  }

  get students(): Student[] {
    return Array.from(this.schoolClass()?.students ?? []);
  }
}
