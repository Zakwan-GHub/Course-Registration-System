/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 14-ep0044TU
 */
package course.registration;

import java.util.ArrayList;
import java.util.List;

public class RegistrationManager {

    private static final double MAX_CREDIT_HOURS = 18.0;

    private final List<Registration> registrations;

    public RegistrationManager() {
        registrations = new ArrayList<>();
    }

    /**
     * Register a student for a course
     * @param student
     * @param course
     * @param semester
     * @return 
     */
    public boolean registerCourse(Student student, Course course, String semester) {

        if (course == null || student == null) {
            return false;
        }

        // Check capacity
        if (course.getCapacity() <= 0) {
            System.out.println("Course is already full.");
            return false;
        }

        // Prevent duplicate registration
        if (isAlreadyRegistered(student, course, semester)) {
            System.out.println("Student already registered for this course.");
            return false;
        }

        // Check credit limit
        double totalCredits = calculateTotalCredits(student, semester);
        if (totalCredits + course.getCreditHours() > MAX_CREDIT_HOURS) {
            System.out.println("Credit hour limit exceeded.");
            return false;
        }

        // Register
        Registration registration = new Registration(student, course, semester);
        registrations.add(registration);
        course.reduceCapacity();

        return true;
    }

    /**
     * Drop a registered course
     * @param student
     * @param course
     * @param semester
     * @return 
     */
    public boolean dropCourse(Student student, Course course, String semester) {

        Registration toRemove = null;

        for (Registration reg : registrations) {
            if (reg.getStudent().getStudentId().equals(student.getStudentId())
                    && reg.getCourse().getCourseCode().equals(course.getCourseCode())
                    && reg.getSemester().equalsIgnoreCase(semester)) {

                toRemove = reg;
                break;
            }
        }

        if (toRemove != null) {
            registrations.remove(toRemove);
            return true;
        }

        return false;
    }

    /**
     * Get all registrations of a student for a semester
     * @param student
     * @param semester
     * @return 
     */
    public List<Registration> getStudentRegistrations(Student student, String semester) {

        List<Registration> result = new ArrayList<>();

        for (Registration reg : registrations) {
            if (reg.getStudent().getStudentId().equals(student.getStudentId())
                    && reg.getSemester().equalsIgnoreCase(semester)) {
                result.add(reg);
            }
        }

        return result;
    }

    /**
     * Calculate total credit hours for a student
     * @param student
     * @param semester
     * @return 
     */
    public double calculateTotalCredits(Student student, String semester) {

        double total = 0.0;

        for (Registration reg : registrations) {
            if (reg.getStudent().getStudentId().equals(student.getStudentId())
                    && reg.getSemester().equalsIgnoreCase(semester)) {
                total += reg.getCreditHours();
            }
        }

        return total;
    }

    /**
     * Check duplicate registration
     */
    private boolean isAlreadyRegistered(Student student, Course course, String semester) {

        for (Registration reg : registrations) {
            if (reg.getStudent().getStudentId().equals(student.getStudentId())
                    && reg.getCourse().getCourseCode().equals(course.getCourseCode())
                    && reg.getSemester().equalsIgnoreCase(semester)) {
                return true;
            }
        }

        return false;
    }
}
