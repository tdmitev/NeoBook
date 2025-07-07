import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Teacher } from '../dtos/teacher';
import { Observable } from 'rxjs';
import { Student } from '../dtos/student';
import { Parent } from '../dtos/parent';

@Injectable({ providedIn: 'root' })
export class UserService {

  private readonly http = inject(HttpClient);

  getTeacherById(id: number): Observable<Teacher> {
    return this.http.get<Teacher>(`localhost:8081/api/teachers/${id}`);
  }

  getStudentById(id: number): Observable<Student> {
    return this.http.get<Student>(`localhost:8081/api/students/${id}`);
  }

  getParentById(id: number): Observable<Parent> {
    return this.http.get<Parent>(`localhost:8081/api/parents/${id}`);
  }
}