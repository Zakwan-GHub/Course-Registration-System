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

public class CourseManager {

    private final List<Course> courseList;

    public CourseManager() {
        courseList = new ArrayList<>();
        loadDefaultCourses();
    }

    /**
     * Load predefined courses into the system
     */
    private void loadDefaultCourses() {

        // BICS courses
        courseList.add(new Course("BICS1301", "Elements of Programming", 3.0, 40));
        courseList.add(new Course("BICS1302", "Introduction to Computer Organisation", 3.0, 40));
        courseList.add(new Course("BICS2301", "Enterprise Networks", 3.0, 40));
        courseList.add(new Course("BICS2302", "Data Structures & Algorithms", 3.0, 40));
        courseList.add(new Course("BICS3301", "Cross-Platform Software Development", 3.0, 40));
        courseList.add(new Course("BICS4331", "Requirements Engineering", 3.0, 40));

        // BIIT courses
        courseList.add(new Course("BIIT1301", "Database Programming", 3.0, 40));
        courseList.add(new Course("BIIT1302", "Organisational Informatics", 3.0, 40));
        courseList.add(new Course("BIIT2301", "User Experience Design", 3.0, 40));
        courseList.add(new Course("BIIT2302", "Management of Information Security", 3.0, 40));
        courseList.add(new Course("BIIT3301", "Cloud Computing", 3.0, 40));
        courseList.add(new Course("BIIT4331", "Business Continuity and Disaster Recovery", 3.0, 40));

        // UNGS courses
        courseList.add(new Course("UNGS1301", "Basic Philosophy and Islamic Worldview", 3.0, 55));
        courseList.add(new Course("UNGS2290", "Knowledge & Civilization in Islam", 2.0, 55));

        // Tilawah / Arabic / Usrah / Leadership
        courseList.add(new Course("TQTD1002", "Tilawah Al-Quran 1", 0.5, 30));
        courseList.add(new Course("LQAD2003", "Arabic for Ibadah 1", 0.5, 30));
        courseList.add(new Course("CCUB1061", "Usrah 1", 0.5, 30));
        courseList.add(new Course("SCSH2163", "Usrah in Action 1 (SD: Community Profiling)", 1.0, 30));
        courseList.add(new Course("SCSH3164", "Usrah in Action 2", 1.0, 30));
        courseList.add(new Course("CCLM2051", "Leadership", 0.5, 30));
    }

    /**
     * Get all available courses
     * @return 
     */
    public List<Course> getAllCourses() {
        return courseList;
    }

    /**
     * Find course by course code
     * @param courseCode
     * @return 
     */
    public Course findCourseByCode(String courseCode) {
        for (Course course : courseList) {
            if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                return course;
            }
        }
        return null;
    }
}
