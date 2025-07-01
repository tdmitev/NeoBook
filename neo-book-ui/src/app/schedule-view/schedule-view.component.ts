import { Component, Input, OnInit } from '@angular/core';
import { CalendarDateFormatter, CalendarModule } from 'angular-calendar';
import { CalendarSchedulerEvent, SchedulerDateFormatter, SchedulerModule } from 'angular-calendar-scheduler';
import { DayOfWeek, getDateOfWeek, SubjectAssignment, TIME_SLOT_TIMES, TimeSlot } from '../dtos/subject-assignment';

export const MOCK_SUBJECT_ASSIGNMENTS: SubjectAssignment[] = [
  {
    id: 1,
    dayOfWeek: DayOfWeek.MONDAY,
    timeSlot: TimeSlot.SLOT_1,
    teacherId: 'b2f1a7f0-33d0-4ad7-b1f9-0a9c25e6bb42',
    subjectId: 101,
    scheduleId: 1
  },
  {
    id: 2,
    dayOfWeek: DayOfWeek.MONDAY,
    timeSlot: TimeSlot.SLOT_2,
    teacherId: 'd7a5c3a0-1a4b-4d9a-8931-bf63c92911c1',
    subjectId: 102,
    scheduleId: 1
  },
  {
    id: 3,
    dayOfWeek: DayOfWeek.TUESDAY,
    timeSlot: TimeSlot.SLOT_3,
    teacherId: 'cd84d234-97f5-4df0-9cc0-2b531f6d02e0',
    subjectId: 103,
    scheduleId: 1
  },
  {
    id: 4,
    dayOfWeek: DayOfWeek.WEDNESDAY,
    timeSlot: TimeSlot.SLOT_1,
    teacherId: 'a0f6e842-c5d4-4c5e-89f4-18c0ee8f0ed5',
    subjectId: 104,
    scheduleId: 1
  },
  {
    id: 5,
    dayOfWeek: DayOfWeek.THURSDAY,
    timeSlot: TimeSlot.SLOT_4,
    teacherId: 'ef9b8924-3403-41e4-9d46-e0f577bf7721',
    subjectId: 105,
    scheduleId: 1
  },
  {
    id: 6,
    dayOfWeek: DayOfWeek.FRIDAY,
    timeSlot: TimeSlot.SLOT_2,
    teacherId: '7342de1e-5b0c-45d2-aed7-65d2bde66e13',
    subjectId: 106,
    scheduleId: 1
  },
  {
    id: 7,
    dayOfWeek: DayOfWeek.FRIDAY,
    timeSlot: TimeSlot.SLOT_3,
    teacherId: '17a519c9-5cf5-4c70-a4db-303d139c999e',
    subjectId: 107,
    scheduleId: 1
  }
];

@Component({
  selector: 'app-schedule-view',
  imports: [CalendarModule, SchedulerModule],
  providers: [
    { provide: CalendarDateFormatter, useClass: SchedulerDateFormatter}
  ],
  templateUrl: './schedule-view.component.html',
})
export class ScheduleViewComponent implements OnInit {
  currentStartOfTheWeek = this.getCurrentStartOfTheWeek();

  events: CalendarSchedulerEvent[] = [];

  subjectAssignments: SubjectAssignment[] = MOCK_SUBJECT_ASSIGNMENTS;

  ngOnInit() {
    this.events = this.subjectAssignments.map(subjectAssignment => this.toEvent(subjectAssignment));
  }

   private getCurrentStartOfTheWeek(): Date {
    const now = new Date();
    return new Date(now.setDate(now.getDate() - now.getDay() + 1));
  }

  private toEvent(assignment: SubjectAssignment): CalendarSchedulerEvent {
    const { dayOfWeek, timeSlot, id, subjectId } = assignment;
    const slotTimes = TIME_SLOT_TIMES[timeSlot];

    const eventDate = getDateOfWeek(dayOfWeek, this.getCurrentStartOfTheWeek());

    const [startHour, startMinute] = slotTimes.start.split(':').map(Number);
    const [endHour, endMinute] = slotTimes.end.split(':').map(Number);

    const start = new Date(eventDate);
    start.setHours(startHour, startMinute, 0, 0);

    const end = new Date(eventDate);
    end.setHours(endHour, endMinute, 0, 0);

    const event: CalendarSchedulerEvent = {
      id: id.toString(),
      start,
      end,
      title: `Subject ${subjectId}`,
      color: { primary: '#1e90ff', secondary: '#D1E8FF' },
      draggable: false,
      resizable: { beforeStart: false, afterEnd: false }
    };

    return event;
  }
}
