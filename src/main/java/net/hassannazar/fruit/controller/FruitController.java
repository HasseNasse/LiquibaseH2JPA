package net.hassannazar.fruit.controller;

import net.hassannazar.common.exception.EntityAlreadyExistsException;
import net.hassannazar.common.exception.NoSuchEntityException;
import net.hassannazar.fruit.model.Fruit;
import net.hassannazar.fruit.model.read.FruitRO;
import net.hassannazar.fruit.repository.FruitRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class FruitController {

    @Inject
    HttpServletRequest requestContext;

    @Inject
    private FruitRepository repository;

    /**
     * @param id
     */
    public FruitRO getFruit (final long id) {
        final var fruitEntity = repository.read(id);
        if (fruitEntity == null)
            throw new NoSuchEntityException("No such fruit entity");

        return fruitEntity.toReadObject();
    }

    /**
     * Returns all of the fruits currently stored on the database.
     *
     * @return list of fruits
     */
    public List<FruitRO> getAllFruits () {
        // Read from repository
        final var data = repository.readAll(0,0);

        // Get all entities and prepare read-objects
        return data.stream()
                .map(Fruit::toReadObject)
                .collect(Collectors.toList());
    }

    /**
     * Creates a Fruit entity object from a FruitRO and attempts
     * to persist it.
     *
     * @param fruit
     * @return id of the newly created fruit
     */
    @Transactional
    public long createFruit (final FruitRO fruit) {
        // Check if fruit entity not already exist
        if (fruit.id != null) {
            final var entity = repository.read(fruit.id);
            if (entity != null)
                throw new EntityAlreadyExistsException(requestContext.getRequestURI(), fruit.id);
        }

        // Create a new persistable entity
        final var fruitEntity = new Fruit(fruit.name, fruit.color, fruit.ripe);

        return repository.create(fruitEntity);
    }
}
