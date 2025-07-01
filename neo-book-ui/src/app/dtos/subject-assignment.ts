import { Teacher } from './teacher';

export interface SubjectAssignment {
  id: number;
  dayOfWeek: DayOfWeek;
  timeSlot: TimeSlot;
  teacherId: string;
  subjectId: number;
  scheduleId: number;
}

export enum DayOfWeek {
  MONDAY = 'MONDAY',
  TUESDAY = 'TUESDAY',
  WEDNESDAY = 'WEDNESDAY',
  THURSDAY = 'THURSDAY',
  FRIDAY = 'FRIDAY',
  SATURDAY = 'SATURDAY',
  SUNDAY = 'SUNDAY'
}

export const getDateOfWeek = (dayOfWeek: DayOfWeek, referenceDate: Date): Date => {
  const dayIndexMap: Record<DayOfWeek, number> = {
    MONDAY: 1,
    TUESDAY: 2,
    WEDNESDAY: 3,
    THURSDAY: 4,
    FRIDAY: 5,
    SATURDAY: 6,
    SUNDAY: 0
  };
  const result = new Date(referenceDate);
  const currentDay = result.getDay();
  const targetDay = dayIndexMap[dayOfWeek];
  const delta = (targetDay - currentDay + 7) % 7;
  result.setDate(result.getDate() + delta);
  return result;
}

export enum TimeSlot {
  SLOT_1 = 'SLOT_1',
  SLOT_2 = 'SLOT_2',
  SLOT_3 = 'SLOT_3',
  SLOT_4 = 'SLOT_4',
  SLOT_5 = 'SLOT_5',
  SLOT_6 = 'SLOT_6',
  SLOT_7 = 'SLOT_7',
  SLOT_8 = 'SLOT_8',
  SLOT_9 = 'SLOT_9',
  SLOT_10 = 'SLOT_10',
  SLOT_11 = 'SLOT_11',
  SLOT_12 = 'SLOT_12',
  SLOT_13 = 'SLOT_13'
}

export const TIME_SLOT_TIMES: Record<TimeSlot, { start: string; end: string }> = {
  SLOT_1: { start: '08:00', end: '08:45' },
  SLOT_2: { start: '08:50', end: '09:35' },
  SLOT_3: { start: '09:40', end: '10:25' },
  SLOT_4: { start: '10:30', end: '11:15' },
  SLOT_5: { start: '11:20', end: '12:05' },
  SLOT_6: { start: '12:10', end: '12:55' },
  SLOT_7: { start: '13:00', end: '13:45' },
  SLOT_8: { start: '13:50', end: '14:35' },
  SLOT_9: { start: '14:40', end: '15:25' },
  SLOT_10: { start: '15:30', end: '16:15' },
  SLOT_11: { start: '16:20', end: '17:05' },
  SLOT_12: { start: '17:10', end: '17:55' },
  SLOT_13: { start: '18:00', end: '18:45' },
};