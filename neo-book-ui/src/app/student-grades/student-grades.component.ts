import { Component, Input, OnInit } from '@angular/core';
import { FullStudent } from '../dtos/student';
import { SubjectWithGrades } from '../dtos/subject-with-grades';
import { MatTableModule } from '@angular/material/table';
import { MatExpansionModule } from '@angular/material/expansion';
import { DatePipe, DecimalPipe } from '@angular/common';
import { Grade } from '../dtos/grade';

@Component({
  imports: [MatTableModule, MatExpansionModule, DatePipe, DecimalPipe],
  selector: 'app-student-grades',
  templateUrl: './student-grades.component.html',
})
export class StudentGradesComponent implements OnInit {
  @Input() fullStudent!: Omit<FullStudent, 'schoolClass' | 'schedule'>;

  subjectGrades: SubjectWithGrades[] = [];

  displayedColumns: string[] = ['value', 'date'];

  ngOnInit(): void {
    this.subjectGrades = this.fullStudent.student.subjects.map(subject => ({
      subject,
      grades: this.fullStudent.grades.filter(g => g.subjectId === subject.id)
    }));
  }

  getSubjectGPA(grades: Grade[]): number {
    if (!grades.length) {
      return 0;
    }

    return grades.reduce((sum, g) => sum + g.value, 0) / grades.length;
  }
}