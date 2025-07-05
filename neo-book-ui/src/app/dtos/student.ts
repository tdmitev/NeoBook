import { Class } from './class';
import { Grade } from './grade';
import { Schedule } from './schedule';
import { School } from './school';
import { SchoolClass } from './school-class';

export interface Subject {
  id: number;
  name: string;
  teacherId: number;
}

export interface Student {
  id: number;
  name: string;
  age: number;
  class?: Class;
  subjects: Subject[];
  scheduleId?: number;
  schoolClassId: number;
  gpa: number;
  parentIds: number[];  // Reference to parent IDs
}

export interface FullStudent {
  student: Student;

  school: School;

  schoolClass: SchoolClass;

  schedule: Schedule;

  grades: Grade[];
}