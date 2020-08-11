package com.edencoding.controllers;

import com.edencoding.animation.GameLoopTimer;
import com.edencoding.controls.KeyPolling;
import com.edencoding.models.Entity;
import com.edencoding.rendering.Renderer;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Ed Eden-Rump
 * @created 27/07/2020 - 07:34
 * @project EdenCoding Space Shooter
 **/
public class GameController implements Initializable {

    public Canvas gameCanvas;
    public AnchorPane gameAnchor;
    KeyPolling keys = KeyPolling.getInstance();

    private Entity player = new Entity(new Image(getClass().getResourceAsStream("/img/ship.png")));

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initialiseCanvas();

        player.setDrawPosition(350, 200);
        player.setScale(0.5f);

        Renderer renderer = new Renderer(this.gameCanvas);
        renderer.addEntity(player);
        renderer.setBackground(new Image(getClass().getResourceAsStream("/img/SpaceBackground.jpg")));

        GameLoopTimer timer = new GameLoopTimer() {
            @Override
            public void tick(float secondsSinceLastFrame) {
                renderer.prepare();

                updatePlayerMovement(secondsSinceLastFrame);

                renderer.render();
            }
        };
        timer.start();
    }

    private void initialiseCanvas() {
        gameCanvas.widthProperty().bind(gameAnchor.widthProperty());
        gameCanvas.heightProperty().bind(gameAnchor.heightProperty());
    }

    private void updatePlayerMovement(float frameDuration) {
        if (keys.isDown(KeyCode.UP)) {
            player.addThrust(20 * frameDuration);
        } else if (keys.isDown(KeyCode.DOWN)) {
            player.addThrust(-20 * frameDuration);
        }

        if (keys.isDown(KeyCode.RIGHT)) {
            player.addTorque(120f * frameDuration);
        } else if (keys.isDown(KeyCode.LEFT)) {
            player.addTorque(-120f * frameDuration);
        }
        player.update();
    }
}
