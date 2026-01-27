/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 14-ep0044TU
 */
package course.registration;

public class Student {

    private final String studentId;
    private final String name;
    private final String email;
    private final String programme;

    public Student(String studentId, String name, String email, String programme) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.programme = programme;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getProgramme() {
        return programme;
    }

    // Convert object to CSV format (for file storage)
    public String toCSV() {
        return studentId + "," + name + "," + email + "," + programme;
    }

    // Convert CSV back to Student object
    public static Student fromCSV(String line) {
        String[] data = line.split(",");
        if (data.length != 4) return null;
        return new Student(data[0], data[1], data[2], data[3]);
    }
}
