import { Student } from './student';

export interface SchoolClass {
  schoolId: number;

  gradeLevel: number;

  gradeLetter: string;

  students: Set<Student>;

  teacherId: number;

  scheduleId: number;
}