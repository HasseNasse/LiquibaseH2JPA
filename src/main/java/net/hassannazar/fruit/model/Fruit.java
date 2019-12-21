package net.hassannazar.fruit.model;

import net.hassannazar.application.model.BaseEntity;
import net.hassannazar.fruit.model.read.FruitRO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Fruit extends BaseEntity {

    private String name;
    private String color;
    private boolean ripe;

    public Fruit () {
    }

    public Fruit (String name, String color, boolean ripe) {
        super();
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

    // Utility methods
    public FruitRO toReadObject() {
        var fruitRO = new FruitRO();

        // convert
        fruitRO.id = this.getId();
        fruitRO.name = this.getName();
        fruitRO.color = this.getColor();
        fruitRO.ripe = this.isRipe();

        return fruitRO;
    }
}
