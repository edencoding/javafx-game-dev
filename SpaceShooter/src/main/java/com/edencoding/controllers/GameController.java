package com.edencoding.controllers;

import com.edencoding.animation.GameLoopTimer;
import com.edencoding.controls.KeyPolling;
import com.edencoding.models.Entity;
import com.edencoding.rendering.Renderer;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Ed Eden-Rump
 * @created 27/07/2020 - 07:34
 * @project EdenCoding Space Shooter
 **/
public class GameController implements Initializable {


    public Canvas gameCanvas;
    KeyPolling keys = KeyPolling.getInstance();

    private Entity player = new Entity(new Image(getClass().getResourceAsStream("/img/ship.png")));

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        player.setDrawPosition(200, 200);

        Renderer renderer = new Renderer(this.gameCanvas);
        renderer.addEntity(player);
        renderer.render();

        GameLoopTimer timer = new GameLoopTimer() {
            @Override
            public void tick(float secondsSinceLastFrame) {
                renderer.prepare();

                updatePlayerMovement();

                renderer.render();
            }
        };
        timer.start();
    }

    private void updatePlayerMovement() {
        if (keys.isDown(KeyCode.UP)) {
            player.addThrust(0.3);
        } else if (keys.isDown(KeyCode.DOWN)) {
            player.addThrust(-0.3);
        }

        if (keys.isDown(KeyCode.RIGHT)) {
            player.addTorque(2f);
        } else if (keys.isDown(KeyCode.LEFT)) {
            player.addTorque(-2f);
        }

        player.update();
    }


}
