import { inject, Injectable } from '@angular/core';
import { merge, mergeMap, Observable } from 'rxjs';
import { Student } from '../dtos/student';
import { HttpClient } from '@angular/common/http';
import { UserService } from './user.service';

@Injectable({ providedIn: 'root' })
export class ParentService {

  private readonly http = inject(HttpClient);

  private readonly userService = inject(UserService);

  getChildren(parentId: number): Observable<Student[]> {
    return this.http.get<any>(`http://localhost:8081/api/parents/${parentId}`)
      .pipe(mergeMap(parent => merge<Student[][]>(
        parent.studentKeycloakUserIds.map((studentId: number) => this.userService.getStudentById(studentId)))))
  }
}