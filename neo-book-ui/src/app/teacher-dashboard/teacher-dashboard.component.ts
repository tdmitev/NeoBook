import { Component, inject, OnInit, signal, WritableSignal } from '@angular/core';
import { Teacher } from '../dtos/teacher';
import { UserService } from '../services/user.service';
import { merge, mergeMap, tap } from 'rxjs';
import { SchoolService } from '../services/school.service';

@Component({
  selector: 'app-teacher-dashboard',
  providers: [UserService, SchoolService],
  templateUrl: './teacher-dashboard.component.html',
})
export class TeacherDashboardComponent implements OnInit {

  private teacher: WritableSignal<Teacher | undefined> = signal(undefined);

  private userService = inject(UserService);
  private schoolService = inject(SchoolService);

  ngOnInit(): void {
    this.userService.getTeacherById(1).pipe(
      tap(teacher => this.teacher.set(teacher)),
      mergeMap(teacher => this.schoolService.getSchoolById(teacher.schoolId))
    )
  }
}
