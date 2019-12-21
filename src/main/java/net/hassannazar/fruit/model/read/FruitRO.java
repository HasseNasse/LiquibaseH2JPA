package net.hassannazar.fruit.model.read;

import net.hassannazar.application.model.read.ReadObject;

import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@JsonbPropertyOrder(PropertyOrderStrategy.LEXICOGRAPHICAL)
public class FruitRO extends ReadObject {

    @NotEmpty
    @Size(min = 3, max = 25)
    public String name;

    @NotEmpty
    @Size(min = 3, max = 25)
    public String color;

    @NotNull
    public boolean ripe;
}
