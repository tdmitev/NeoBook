import { inject, Injectable } from '@angular/core';
import { School } from '../dtos/school';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { SchoolClass } from '../dtos/school-class';
import { Schedule } from '../dtos/schedule';

@Injectable({ providedIn: 'root' })
export class SchoolService {

  private readonly http = inject(HttpClient);

  getSchools(): Observable<School[]> {
    return this.http.get<School[]>('localhost:8082/api/schools');
  }

  getSchoolById(id: number): Observable<School> {
    return this.http.get<School>(`localhost:8082/api/schools/${id}`);
  }

  getSchoolClass(schoolClassId: number): Observable<SchoolClass> {
    return this.http.get<SchoolClass>(`localhost:8082/api/school-classes/${schoolClassId}`)
  }

  getScheduleById(scheduleId: number): Observable<Schedule> {
    return this.http.get<Schedule>(`localhost:8082/api/schedule/${scheduleId}`);
  }

  getSchoolDetails(): Observable<any> {
    return of();
  }
}