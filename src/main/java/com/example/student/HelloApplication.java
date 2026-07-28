package com.example.student;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        String css = this.getClass().getResource("style.css").toExternalForm();
        scene.getStylesheets().add(css);
        // scene.getStylesheets().add(HelloApplication.class.getResource("style.css").toExternalForm());
        stage.setTitle("STUDENT MANAGEMENT SYSTEM");
        stage.setScene(scene);
        stage.show();
    }
  //  public static void main(String[] args) {
  //     launch();
   // }
}
