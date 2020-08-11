package com.edencoding.models;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class Entity {

    Point2D position;
    float rotation;
    float scale;
    double width;
    double height;

    Image entityImage;

    public Entity(Image entityImage) {
        this.entityImage = entityImage;
        this.width = entityImage.getWidth();
        this.height = entityImage.getHeight();
    }

    public void move(Point2D movement){
        this.position = this.position.add(movement);
    }

    public void rotate(float rotation){
        this.rotation += rotation;
    }

    public void scale(float scale){
        this.scale = this.scale * scale;
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(float x, float y) {
        this.position = new Point2D(x, y);
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation){
        this.rotation = rotation;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public Image getImage() {
        return entityImage;
    }

    public void setImage(Image entityImage) {
        this.entityImage = entityImage;
    }

    public double getWidth() {
        return this.width * getScale();
    }

    public double getHeight(){
        return this.height * getScale();
    }
}
