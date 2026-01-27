
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 14-ep0044TU
 */
package course.registration;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CourseRegistrationPage {

    private final CourseManager courseManager;
    private final RegistrationManager registrationManager;
    private final Student student;
    private final String semester;

    public CourseRegistrationPage(Student student,
                                  String semester,
                                  CourseManager courseManager,
                                  RegistrationManager registrationManager) {
        this.student = student;
        this.semester = semester;
        this.courseManager = courseManager;
        this.registrationManager = registrationManager;
    }

    public void show(Stage stage) {

        // Title
        Label titleLabel = new Label("Course Registration");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label studentLabel = new Label(
                "Student: " + student.getName() +
                " (" + student.getStudentId() + ")"
        );

        // Course List
        ObservableList<Course> courses =
                FXCollections.observableArrayList(courseManager.getAllCourses());

        ListView<Course> courseListView = new ListView<>(courses);
        courseListView.setPrefHeight(250);

        // display text in ListView
        courseListView.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Course course, boolean empty) {
                super.updateItem(course, empty);
                if (empty || course == null) {
                    setText(null);
                } else {
                    setText(
                        course.getCourseCode() + " - " +
                        course.getCourseName() +
                        " | Credit: " + course.getCreditHours() +
                        " | Capacity: " + course.getCapacity()
                    );
                }
            }
        });

        // Message Label
        Label messageLabel = new Label();

        // Register Button
        Button registerButton = new Button("Register Course");

        registerButton.setOnAction(e -> {
            Course selectedCourse =
                    courseListView.getSelectionModel().getSelectedItem();

            if (selectedCourse == null) {
                messageLabel.setText("Please select a course.");
                return;
            }

            boolean success = registrationManager.registerCourse(
                    student,
                    selectedCourse,
                    semester
            );

            if (success) {

                // Success Alert
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Message");
                alert.setHeaderText(null);
                alert.setContentText("Registration successful!");

                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {

                        // Go To Summary Page
                        RegistrationSummaryPage summaryPage =
                                new RegistrationSummaryPage(
                                        student,
                                        semester,
                                        registrationManager
                                );

                        Stage summaryStage = new Stage();
                        summaryPage.show(summaryStage);

                        // close current window
                        stage.close();
                    }
                });

            } else {
                messageLabel.setText(
                        "Registration failed. Course may be full or already registered."
                );
            }
        });

        // Layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(
                titleLabel,
                studentLabel,
                courseListView,
                registerButton,
                messageLabel
        );

        // Scene
        Scene scene = new Scene(layout, 550, 420);
        stage.setTitle("Course Registration");
        stage.setScene(scene);
        stage.show();
    }
}
