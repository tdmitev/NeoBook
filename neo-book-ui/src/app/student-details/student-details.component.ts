import { Component, inject, Input, OnInit, signal, WritableSignal } from '@angular/core';
import { FullStudent } from '../dtos/student';
import { StudentService } from '../services/student.service';
import { ScheduleViewComponent } from "../schedule-view/schedule-view.component";
import { StudentGradesComponent } from '../student-grades/student-grades.component';

@Component({
  selector: 'app-student-details',
  imports: [ScheduleViewComponent, StudentGradesComponent],
  providers: [StudentService],
  templateUrl: './student-details.component.html',
})
export class StudentDetailsComponent implements OnInit {

  private readonly studentService = inject(StudentService);

  private fullStudent: Omit<FullStudent, 'schoolClass' | 'schedule'> = {
    student: {
      id: 1,
      name: 'Alice Johnson',
      age: 16,
      schoolClassId: 101,
      gpa: 3.75,
      parentIds: [1001, 1002],
      subjects: [
        { id: 1, name: 'Mathematics', teacherId: 201 },
        { id: 2, name: 'English', teacherId: 202 },
        { id: 3, name: 'History', teacherId: 203 },
      ],
      scheduleId: 301,
    },

    school: {
      id: 1,
      name: 'Springfield High School',
      address: '123 Elm St, Springfield',
    },

    grades: [
      // Math grades
      {
        subjectId: 1,
        studentId: 1,
        value: 5.0,
        date: new Date('2024-09-01T00:00:00Z'),
      },
      {
        subjectId: 1,
        studentId: 1,
        value: 4.5,
        date: new Date('2024-10-03T00:00:00Z'),
      },

      // English grades
      {
        subjectId: 2,
        studentId: 1,
        value: 3.8,
        date: new Date('2024-09-12T00:00:00Z'),
      },

      // History grades
      {
        subjectId: 3,
        studentId: 1,
        value: 4.2,
        date: new Date('2024-10-10T00:00:00Z'),
      },
      {
        subjectId: 3,
        studentId: 1,
        value: 4.7,
        date: new Date('2024-11-15T00:00:00Z'),
      },
    ],
};

  @Input()
  studentId!: number;

  student: WritableSignal<Omit<FullStudent, 'schoolClass' | 'schedule'> | undefined> = signal(undefined);

  ngOnInit(): void {
    this.student.set(this.fullStudent);
    // this.studentService.getStudent(this.studentId).subscribe(this.student.set.bind(this));
  }

}
