package net.hassannazar.fruit.model.read;

import net.hassannazar.common.model.read.ReadObject;
import net.hassannazar.fruit.model.FruitColor;

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

    @NotNull
    public FruitColor color;

    @NotNull
    public boolean ripe;
}
