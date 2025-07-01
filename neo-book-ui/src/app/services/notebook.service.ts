import { inject, Injectable } from '@angular/core';
import { Grade } from '../dtos/grade';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class NotebookService {

  private http = inject(HttpClient);

  getGrades(studentId: number): Observable<Grade[]> {
    return this.http.get<Grade[]>(`http://localhost:8081/api/grades?studentId=${studentId}`)
  }
}