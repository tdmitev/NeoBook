import { Routes } from '@angular/router';
import { RoleGuard } from './auth/role.guard';
import { AuthComponent } from './auth/auth.component';
import { ParentDashboardComponent } from './parent-portal/parent-dashboard/parent-dashboard.component';
import { StudentDetailsComponent } from './student-details/student-details.component';
import { TeacherDashboardComponent } from './teacher-dashboard/teacher-dashboard.component';

export const routes: Routes = [
  // Auth routes
  { path: 'auth', component: AuthComponent },

  // Student routes
  {
    path: 'students',
    // canActivate: [RoleGuard],
    // data: { roles: ['student'] },
    children: [
      {
        path: ':id',
        component: StudentDetailsComponent,
        canActivate: [RoleGuard],
        data: { roles: ['student', 'teacher'] },
      },
    ]
  },

  // Teacher routes
  {
    path: 'teacher/:id',
    component: TeacherDashboardComponent,
    canActivate: [RoleGuard],
    data: { roles: ['teacher'] }
  },

  // Parent portal routes
  {
    path: 'parent-portal',
    children: [
      {
        path: '',
        component: ParentDashboardComponent,
        canActivate: [RoleGuard],
        data: { roles: ['parent'] }
      }
    ]
  },
];
