/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 14-ep0044TU
 */
package course.registration;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {

    private final String FILE_NAME = "students.txt";

    /**
     * Register a new student
     * @param student Student object
     * @return true if registration successful, false otherwise
     */
    public boolean registerStudent(Student student) {

        // Prevent duplicate student ID
        if (isStudentExists(student.getStudentId())) {
            return false;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(student.toCSV());
            writer.newLine();
            return true;
        } catch (IOException e) {
            System.out.println("Error registering student.");
            return false;
        }
    }

    /**
     * Check if a student ID already exists
     */
    private boolean isStudentExists(String studentId) {
        for (Student s : getAllStudents()) {
            if (s.getStudentId().equalsIgnoreCase(studentId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieve all students from file
     * @return 
     */
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();

        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return students;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Student student = Student.fromCSV(line);
                if (student != null) {
                    students.add(student);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading student file.");
        }

        return students;
    }

    /**
     * Find a student by student ID
     * @param studentId
     * @return 
     */
    public Student findStudentById(String studentId) {
        for (Student student : getAllStudents()) {
            if (student.getStudentId().equalsIgnoreCase(studentId)) {
                return student;
            }
        }
        return null;
    }
}
