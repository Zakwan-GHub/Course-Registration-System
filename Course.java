/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 14-ep0044TU
 */
package course.registration;

public class Course {

    private final String courseCode;
    private final String courseName;
    private final double creditHours;
    private int capacity;

    public Course(String courseCode, String courseName, double creditHours, int capacity) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.creditHours = creditHours;
        this.capacity = capacity;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public double getCreditHours() {
        return creditHours;
    }

    public int getCapacity() {
        return capacity;
    }

    public void reduceCapacity() {
        if (capacity > 0) {
            capacity--;
        }
    }

    @Override
    public String toString() {
        return courseCode + " - " + courseName +
               " | Credit: " + creditHours +
               " | Capacity: " + capacity;
    }
}

