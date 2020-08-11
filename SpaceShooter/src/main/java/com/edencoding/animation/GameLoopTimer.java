package com.edencoding.animation;

import javafx.animation.AnimationTimer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * @author Ed Eden-Rump
 * @created 27/07/2020 - 07:34
 * @project EdenCoding Space Shooter
 **/
public abstract class GameLoopTimer extends AnimationTimer {

    long pauseStart;
    long animationStart;
    DoubleProperty animationDuration = new SimpleDoubleProperty(0L);

    long lastFrameTimeNanos;

    boolean isPaused;
    boolean isActive;

    boolean pauseScheduled;
    boolean playScheduled;
    boolean restartScheduled;

    public boolean isPaused() {
        return isPaused;
    }

    public boolean isActive() {
        return isActive;
    }

    public DoubleProperty animationDurationProperty() {
        return animationDuration;
    }

    public void pause() {
        if (!isPaused) {
            pauseScheduled = true;
        }
    }

    public void play() {
        if (isPaused) {
            playScheduled = true;
        }
    }

    @Override
    public void start() {
        super.start();
        isActive = true;
        restartScheduled = true;
    }

    @Override
    public void stop() {
        super.stop();
        pauseStart = 0;
        isPaused = false;
        isActive = false;
        pauseScheduled = false;
        playScheduled = false;
        animationDuration.set(0);
    }

    @Override
    public void handle(long now) {
        if (pauseScheduled) {
            pauseStart = now;
            isPaused = true;
            pauseScheduled = false;
        }

        if (playScheduled) {
            animationStart += (now - pauseStart);
            isPaused = false;
            playScheduled = false;
        }

        if (restartScheduled) {
            isPaused = false;
            animationStart = now;
            restartScheduled = false;
        }

        if (!isPaused) {
            long animDuration = now - animationStart;
            animationDuration.set(animDuration / 1e9);

            float secondsSinceLastFrame = (float) ((now - lastFrameTimeNanos) / 1e9);
            lastFrameTimeNanos = now;
            tick(secondsSinceLastFrame);
        }
    }

    public abstract void tick(float secondsSinceLastFrame);
}
