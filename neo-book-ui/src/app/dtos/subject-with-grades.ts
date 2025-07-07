import { Grade } from './grade';
import { Subject } from './student';

export interface SubjectWithGrades {
  subject: Subject;
  grades: Grade[];
}