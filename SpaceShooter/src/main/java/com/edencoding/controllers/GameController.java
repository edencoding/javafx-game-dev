package com.edencoding.controllers;

import com.edencoding.animation.GameLoopTimer;
import com.edencoding.controls.KeyPolling;
import com.edencoding.models.Entity;
import com.edencoding.rendering.Renderer;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Ed Eden-Rump
 * @created 27/07/2020 - 07:34
 * @project EdenCoding Space Shooter
 **/
public class GameController implements Initializable {


    public Canvas gameCanvas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        KeyPolling polling = KeyPolling.getInstance();

        Renderer renderer = new Renderer(this.gameCanvas.getGraphicsContext2D());

        Entity player = new Entity(new Image(getClass().getResourceAsStream("/img/ship.png")));
        player.setPosition(200, 200);

        renderer.render();

        this.gameCanvas.getGraphicsContext2D().drawImage(new Image(getClass().getResourceAsStream("/img/EdenCodingIcon.png")), 200, 200);

        GameLoopTimer timer = new GameLoopTimer() {
            @Override
            public void tick(float secondsSinceLastFrame) {
                System.out.println(polling.toString());
            }
        };
        timer.start();
    }

}
