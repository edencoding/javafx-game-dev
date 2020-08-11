package com.edencoding;

import com.edencoding.controls.KeyPolling;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @author Ed Eden-Rump
 * @created 27/07/2020 - 07:34
 * @project EdenCoding Space Shooter
 **/
public class SpaceShooter extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainView.fxml"));
        Scene scene = new Scene(root);

        KeyPolling.getInstance().pollScene(scene);

        primaryStage.setScene(scene);
        primaryStage.setTitle("EdenCoding - SimpleSpaceShooter");

        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/img/EdenCodingIcon.png")));

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
