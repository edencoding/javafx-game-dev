package com.edencoding.controls;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Ed Eden-Rump
 * @created 11/08/2020 - 11:20
 * @project EdenCoding Space Shooter
 **/
public class KeyPolling {

    private static Scene scene;
    private static final Set<KeyCode> keysCurrentlyDown = new HashSet<>();

    private KeyPolling() {
    }

    public static KeyPolling getInstance() {
        return new KeyPolling();
    }

    public void pollScene(Scene scene) {
        clearKeys();
        removeCurrentKeyHandlers();
        setScene(scene);
    }

    private void clearKeys() {
        keysCurrentlyDown.clear();
    }

    private void removeCurrentKeyHandlers() {
        if (scene != null) {
            KeyPolling.scene.setOnKeyPressed(null);
            KeyPolling.scene.setOnKeyReleased(null);
        }
    }

    private void setScene(Scene scene) {
        KeyPolling.scene = scene;
        KeyPolling.scene.setOnKeyPressed((keyEvent -> {
            keysCurrentlyDown.add(keyEvent.getCode());
            System.out.println("Adding - " + keyEvent.getCode().toString());
        }));
        KeyPolling.scene.setOnKeyReleased((keyEvent -> {
            keysCurrentlyDown.remove(keyEvent.getCode());
        }));
    }

    public boolean isDown(KeyCode keyCode) {
        return keysCurrentlyDown.contains(keyCode);
    }

    @Override
    public String toString() {
        StringBuilder keysDown = new StringBuilder("KeyPolling on scene (").append(scene).append(")");
        for (KeyCode code : keysCurrentlyDown) {
            keysDown.append(code.getName()).append(" ");
        }
        return keysDown.toString();
    }
}
