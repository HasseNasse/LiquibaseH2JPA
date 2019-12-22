package net.hassannazar.fruit.controller;

import net.hassannazar.application.exception.EntityAlreadyExistsException;
import net.hassannazar.application.exception.NoSuchEntityException;
import net.hassannazar.fruit.model.Fruit;
import net.hassannazar.fruit.model.read.FruitRO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class FruitController {

    @Inject
    private HttpServletRequest requestContext;

    @PersistenceContext(unitName = "fruitsPU")
    EntityManager em;

    /**
     *
     * @param id
     */
    public FruitRO getFruit (long id) {
        final var fruitEntity = em.find(Fruit.class, id);
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
        /// CriteriaBuilder interface is the main gateway into the Criteria API,
        /// acting as a factory for the various objects that link together to form
        /// a query definition.
        final var criteriaBuilder = em.getCriteriaBuilder();
        /// CriteriaQuery object forms the shell of the query definition and generally 
        /// contains the methods that match up with the JP QL query clauses.
        final var criteriaQuery = criteriaBuilder.createQuery(Fruit.class);
        /// The first step is to establish the root of the query by invoking from() to
        /// get back a Root object. This is equivalent to declaring the identification
        /// variable e in the JP QL example and the Root object will form the basis for
        /// path expressions in the rest of the query.
        final var all = criteriaQuery.select(criteriaQuery.from(Fruit.class));
        // execute Criteria Query
        final var allQuery = em.createQuery(all);

        // Get all entities and prepare read-objects
        return allQuery.getResultList().stream()
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
    public long createFruit (FruitRO fruit) {
        // Check if fruit entity not already exist
        if (fruit.id != null) {
            final var entity = em.find(Fruit.class, fruit.id);
            if (entity != null)
                throw new EntityAlreadyExistsException(requestContext.getRequestURI(), fruit.id);
        }

        // Create a new persistable entity
        var fruitEntity = new Fruit(fruit.name, fruit.color, fruit.ripe);
        fruitEntity.setModifier(requestContext.getLocalAddr());
        fruitEntity.setDateModified(fruitEntity.getCreated());

        // Persist the entity to DB
        em.persist(fruitEntity);
        // ID is only guaranteed to be generated at flush time
        em.flush();

        return fruitEntity.getId();
    }
}
