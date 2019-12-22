package net.hassannazar.fruit.repository;

import net.hassannazar.common.repository.CRUDRepository;
import net.hassannazar.fruit.model.Fruit;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class FruitRepository implements CRUDRepository<Fruit> {

    @Inject
    HttpServletRequest requestContext;

    @PersistenceContext(unitName = "fruitsPU")
    EntityManager entityManager;

    @Override
    @Transactional
    public long create (Fruit fruit) {
        // Set modified date == created for new entities
        fruit.setDateModified(fruit.getCreated());
        fruit.setModifier(requestContext.getRemoteAddr());

        // Persist the entity to DB
        entityManager.persist(fruit);
        /// ID is only guaranteed to be generated at flush time or after
        /// the transaction is committed.
        entityManager.flush();

        return fruit.getId();
    }

    @Override
    public Fruit read (long id) {
        return entityManager.find(Fruit.class, id);
    }

    @Override
    public List<Fruit> readAll (int offset, int limit) {
        /// CriteriaBuilder interface is the main gateway into the Criteria API,
        /// acting as a factory for the various objects that link together to form
        /// a query definition.
        final var criteriaBuilder = entityManager.getCriteriaBuilder();
        /// CriteriaQuery object forms the shell of the query definition and generally
        /// contains the methods that match up with the JP QL query clauses.
        final var criteriaQuery = criteriaBuilder.createQuery(Fruit.class);
        /// The first step is to establish the root of the query by invoking from() to
        /// get back a Root object. This is equivalent to declaring the identification
        /// variable e in the JP QL example and the Root object will form the basis for
        /// path expressions in the rest of the query.
        final var all = criteriaQuery.select(criteriaQuery.from(Fruit.class));
        // execute Criteria Query
        return entityManager.createQuery(all).getResultList();
    }

    @Override
    public Fruit update (Fruit fruit) {
        return null;
    }

    @Override
    public void delete (long id) {

    }
}
