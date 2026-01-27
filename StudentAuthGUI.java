/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package course.registration;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StudentAuthGUI extends Application {

    private final StudentManager studentManager = new StudentManager();

    @Override
    public void start(Stage stage) {

        // Labels
        Label titleLabel = new Label("Student Registration");
        Label idLabel = new Label("Student ID:");
        Label nameLabel = new Label("Name:");
        Label emailLabel = new Label("Email:");
        Label programmeLabel = new Label("Programme:");

        // Text fields
        TextField idField = new TextField();
        TextField nameField = new TextField();
        TextField emailField = new TextField();
        TextField programmeField = new TextField();

        // Button
        Button registerButton = new Button("Register");

        // Message label
        Label messageLabel = new Label();

        // Button action
        registerButton.setOnAction((ActionEvent e) -> {
            String id = idField.getText();
            String name = nameField.getText();
            String email = emailField.getText();
            String programme = programmeField.getText();
            
            if (id.isEmpty() || name.isEmpty() || email.isEmpty() || programme.isEmpty()) {
                messageLabel.setText("Please fill in all fields.");
                return;
            }
            
            Student student = new Student(id, name, email, programme);
            boolean success = studentManager.registerStudent(student);
            
            if (success) {
                CourseManager courseManager = new CourseManager();
                RegistrationManager registrationManager = new RegistrationManager();
                
                String semester = "2025/2026 Semester 1";
                
                CourseRegistrationPage coursePage =
                        new CourseRegistrationPage(student, semester, courseManager, registrationManager);
                
                Stage nextStage = new Stage();
                coursePage.show(nextStage);
                
                Stage currentStage = (Stage) registerButton.getScene().getWindow();
                currentStage.close();
            }
        });

        // Layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        grid.add(titleLabel, 0, 0, 2, 1);
        grid.add(idLabel, 0, 1);
        grid.add(idField, 1, 1);
        grid.add(nameLabel, 0, 2);
        grid.add(nameField, 1, 2);
        grid.add(emailLabel, 0, 3);
        grid.add(emailField, 1, 3);
        grid.add(programmeLabel, 0, 4);
        grid.add(programmeField, 1, 4);
        grid.add(registerButton, 1, 5);
        grid.add(messageLabel, 1, 6);

        // Scene
        Scene scene = new Scene(grid, 400, 350);
        stage.setTitle("Course Registration System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
