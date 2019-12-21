package net.hassannazar.fruit.model;

import net.hassannazar.application.model.BaseEntity;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class Fruit extends BaseEntity {
    private String name;
    private String color;
    private boolean ripe;

    public Fruit (String name, String color, boolean ripe, String modifier) {
        super(LocalDateTime.now(), modifier);
        this.name = name;
        this.color = color;
        this.ripe = ripe;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getColor () {
        return color;
    }

    public void setColor (String color) {
        this.color = color;
    }

    public boolean isRipe () {
        return ripe;
    }

    public void setRipe (boolean ripe) {
        this.ripe = ripe;
    }
}
