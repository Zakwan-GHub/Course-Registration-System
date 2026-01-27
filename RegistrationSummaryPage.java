
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 14-ep0044TU
 */
package course.registration;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RegistrationSummaryPage {

    private final Student student;
    private final String semester;
    private final RegistrationManager registrationManager;

    public RegistrationSummaryPage(Student student,
                                   String semester,
                                   RegistrationManager registrationManager) {
        this.student = student;
        this.semester = semester;
        this.registrationManager = registrationManager;
    }

    public void show(Stage stage) {

        // Title
        Label titleLabel = new Label("COURSE REGISTRATION SUMMARY");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        // Student Info
        Label studentLabel = new Label(
                "Student Name : " + student.getName() +
                "\nStudent ID   : " + student.getStudentId() +
                "\nProgramme    : " + student.getProgramme() +
                "\nSemester     : " + semester
        );

        Separator separator1 = new Separator();

        // Fetch registrations
        List<Registration> registrations =
                registrationManager.getStudentRegistrations(student, semester);

        VBox courseBox = new VBox(5);
        double totalCredits = 0.0;

        for (Registration reg : registrations) {
            Course c = reg.getCourse();

            Label courseLabel = new Label(
                    c.getCourseCode() + " - " +
                    c.getCourseName() +
                    " (" + c.getCreditHours() + " credit)"
            );

            courseBox.getChildren().add(courseLabel);
            totalCredits += c.getCreditHours();
        }

        Separator separator2 = new Separator();

        // Total credits
        Label totalCreditLabel =
                new Label("Total Credit Hours: " + totalCredits);

        // Registration date
        Label dateLabel = new Label(
                "Registration Date: " +
                registrations.get(0)
                        .getRegistrationDate()
                        .format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
        );

        // Buttons
        Button proceedButton = new Button("Proceed to Receipt");
        Button backButton = new Button("Back");

        // Navigate to Receipt Page
        proceedButton.setOnAction(e -> {
            RegistrationReceiptPage receiptPage =
                    new RegistrationReceiptPage(student, semester, registrationManager);

            Stage receiptStage = new Stage();
            receiptPage.show(receiptStage);

            stage.close();
        });

        // Close Summary
        backButton.setOnAction(e -> stage.close());

        // Layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(
                titleLabel,
                studentLabel,
                separator1,
                courseBox,
                separator2,
                totalCreditLabel,
                dateLabel,
                proceedButton,
                backButton
        );

        Scene scene = new Scene(layout, 500, 450);
        stage.setTitle("Registration Summary");
        stage.setScene(scene);
        stage.show();
    }
}
