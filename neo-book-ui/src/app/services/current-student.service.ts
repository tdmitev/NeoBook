import { inject, Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { FullStudent } from '../dtos/student';
import { StudentService } from './student.service';

@Injectable({ providedIn: 'root' })
export class CurrentStudentService {

  private studentService = inject(StudentService);

  private currentStudent = new BehaviorSubject<FullStudent | undefined>(undefined);

  currentStudent$ = this.currentStudent.asObservable();

  loadCurrentStudent() {
    const id = 1;
    this.studentService.getStudent(id)
    .subscribe(student => {
      this.currentStudent.next(student);
    });
  }
}