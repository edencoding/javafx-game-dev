package com.edencoding.rendering;

import com.edencoding.models.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.List;

public class Renderer {

    GraphicsContext context;

    List<Entity> entities;

    public Renderer(GraphicsContext context) {
        this.context = context;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public void clearEntities() {
        entities.clear();
    }

    public void render() {
        for (Entity entity : entities) {
            Image image = entity.getImage();
            context.drawImage(
                    entity.getImage(),
                    entity.getPosition().getX(),
                    entity.getPosition().getY(),
                    entity.getWidth(),
                    entity.getHeight()
            );
        }
    }


}
