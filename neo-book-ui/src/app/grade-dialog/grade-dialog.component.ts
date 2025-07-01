import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { FormControl, Validators } from '@angular/forms';
import { NotebookService } from '../services/notebook.service';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-grade-dialog',
  imports: [
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule
  ],
  templateUrl: './grade-dialog.component.html',
})
export class GradeDialogComponent {
  scoreControl = new FormControl<number | null>(null, [
    Validators.required,
    Validators.min(2),
    Validators.max(6),
  ]);

  constructor(
    private dialogRef: MatDialogRef<GradeDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { studentId: number, subjectId: number, teacherId: number },
    private notebookService: NotebookService
  ) {}

  submit(): void {
    if (this.scoreControl.invalid) return;

    const score = this.scoreControl.value!;

    const { studentId, subjectId, teacherId } = this.data;

    this.notebookService.addGrade(studentId, subjectId, score, teacherId).subscribe({
      next: () => this.dialogRef.close(),
      error: (err) => console.error('Submission failed', err),
    });
  }

  cancel(): void {
    this.dialogRef.close();
  }
}
