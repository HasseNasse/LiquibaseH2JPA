package net.hassannazar.fruit.model;

import net.hassannazar.common.model.BaseEntity;
import net.hassannazar.fruit.model.read.FruitRO;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Entity
public class Fruit extends BaseEntity {

    private String name;
    @Enumerated(EnumType.STRING)
    private FruitColor color;
    private boolean ripe;

    public Fruit () {
    }

    public Fruit (final String name, final FruitColor color, final boolean ripe) {
        super();
        this.name = name;
        this.color = color;
        this.ripe = ripe;
    }

    public String getName () {
        return name;
    }

    public void setName (final String name) {
        this.name = name;
    }

    public FruitColor getColor () {
        return color;
    }

    public void setColor (final FruitColor color) {
        this.color = color;
    }

    public boolean isRipe () {
        return ripe;
    }

    public void setRipe (final boolean ripe) {
        this.ripe = ripe;
    }

    // Utility methods
    public FruitRO toReadObject() {
        final var fruitRO = new FruitRO();

        // convert
        fruitRO.id = getId();
        fruitRO.name = getName();
        fruitRO.color = getColor();
        fruitRO.ripe = isRipe();

        return fruitRO;
    }
}
