/*
 * Course: CS-1021-051
 * Winter 2019
 * Lab 6 - Exceptions
 * Name: Brendan Ecker
 * Created: 01/17/19
 */
package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is the main class that runs everything
 * then adds it to one stage with a desired
 * width and height, then shows the stage.
 */
public class Main extends Application {
    /**
     * The method that creates everything that
     * is in the sample.fxml and puts it all into
     * one stage.
     *
     * @param primaryStage  The Stage that everything is added
     *                      to and then shown.
     * @throws Exception    This method may throw many types
     *                      of exceptions.
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Website tester!");
        primaryStage.setScene(new Scene(root, 555, 700));
        primaryStage.show();
        primaryStage.setResizable(false);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
