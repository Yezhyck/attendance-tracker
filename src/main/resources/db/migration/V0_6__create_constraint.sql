alter table if exists lessons
    add constraint id_study_class_fk foreign key (id_study_class) references study_classes;

alter table if exists lessons_students
    add constraint id_absence_reason_fk foreign key (id_absence_reason) references absence_reasons;

alter table if exists lessons_students
    add constraint id_attendance_status_fk foreign key (id_attendance_status) references attendance_statuses;

alter table if exists lessons_students
    add constraint id_lesson_fk foreign key (id_lesson) references lessons;

alter table if exists lessons_students
    add constraint id_student_lessons_students_fk foreign key (id_student) references students;

alter table if exists study_classes_students
    add constraint id_student_study_classes_students_fk foreign key (id_student) references students;

alter table if exists study_classes_students
    add constraint id_study_class_fk foreign key (id_study_class) references study_classes;

alter table if exists study_classes
    add constraint id_user_fk foreign key (id_user) references users;