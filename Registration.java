
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 14-ep0044TU
 */
package course.registration;

import java.time.LocalDate;

public class Registration {

    private final Student student;
    private final Course course;
    private final String semester;
    private final LocalDate registrationDate;

    public Registration(Student student, Course course, String semester) {
        this.student = student;
        this.course = course;
        this.semester = semester;
        this.registrationDate = LocalDate.now();
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public String getSemester() {
        return semester;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public double getCreditHours() {
        return course.getCreditHours();
    }

    public String toCSV() {
        return student.getStudentId() + "," +
               course.getCourseCode() + "," +
               semester + "," +
               registrationDate;
    }
}

