import { inject, Injectable } from '@angular/core';
import { forkJoin, mergeMap, Observable, of } from 'rxjs';
import { FullStudent } from '../dtos/student';
import { UserService } from './user.service';
import { SchoolService } from './school.service';
import { NotebookService } from './notebook.service';

@Injectable({ providedIn: 'root' })
export class StudentService {

  private readonly userService = inject(UserService);

  private readonly schoolService = inject(SchoolService);

  private readonly notebookService = inject(NotebookService);

  getStudent(studentId: number): Observable<FullStudent | undefined> {
    return this.userService.getStudentById(studentId).pipe(
      mergeMap(student => {
        return forkJoin({ student: of(student), schoolClass: this.schoolService.getSchoolClass(student.schoolClassId) })
      }),
      mergeMap(({ student, schoolClass}) => forkJoin({
          student: of(student),
          schoolClass: of(schoolClass),
          schedule: this.schoolService.getScheduleById(schoolClass.scheduleId) })
      ),
      mergeMap(({ student, schoolClass, schedule }) =>
        forkJoin({
          student: of(student),
          schoolClass: of(schoolClass),
          schedule: of(schedule),
          school: this.schoolService.getSchoolById(schoolClass.schoolId)
        })
      ),
      mergeMap(({ student, schoolClass, schedule, school }) =>
        forkJoin({
          student: of(student),
          schoolClass: of(schoolClass),
          schedule: of(schedule),
          school: of(school),
          grades: this.notebookService.getGrades(studentId)
        })
      )
    )
  };
}