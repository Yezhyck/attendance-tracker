alter table if exists lessons
    drop constraint if exists id_study_class_fk;

alter table if exists lessons_students
    drop constraint if exists id_absence_reason_fk;

alter table if exists lessons_students
    drop constraint if exists id_attendance_status_fk;

alter table if exists lessons_students
    drop constraint if exists id_lesson_fk;

alter table if exists lessons_students
    drop constraint if exists id_student_lessons_students_fk;

alter table if exists study_classes_students
    drop constraint if exists id_student_study_classes_students_fk;

alter table if exists study_classes_students
    drop constraint if exists id_study_class_fk;

alter table if exists study_classes_students
    drop constraint if exists id_user_fk;