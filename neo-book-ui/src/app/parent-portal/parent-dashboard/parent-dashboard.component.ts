import { Component, inject, Input, OnInit, signal, WritableSignal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Parent } from '../../dtos/parent';
import { MatCardModule } from '@angular/material/card';
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { StudentListComponent } from "../../student-list/student-list.component";
import { ParentService } from '../../services/parent.service';
import { Student } from '../../dtos/student';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-parent-dashboard',
  templateUrl: './parent-dashboard.component.html',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    MatListModule,
    MatButtonModule,
    MatIconModule,
    StudentListComponent
]
})
export class ParentDashboardComponent implements OnInit {
  private readonly parentService = inject(ParentService);

  private readonly userService = inject(UserService);

  @Input()
  parentId!: number;

  parent: WritableSignal<Parent | undefined> = signal(undefined);

  children: WritableSignal<Student[]> = signal([]);

  ngOnInit() {
    this.loadParentData(this.parentId);
  }

  private loadParentData(parentId: number) {
    this.userService.getParentById(parentId).subscribe(parent => {
      this.parent.set(parent);
    });

    this.parentService.getChildren(parentId).subscribe(children => {
      this.children.set(children);
    });
  }
}