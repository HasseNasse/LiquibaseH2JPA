package net.hassannazar.application.liquibase;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.Resource;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.SQLException;

@Dependent
public class LiquibaseUpdater {

    @Resource(lookup = "jdbc/h2test")
    DataSource dataSource;

    @Inject
    @ConfigProperty(name = "liquibase.should.run.update")
    private boolean shouldUpdate;

    /**
     * Performs a liquibase update by looking at the db.changelog-master.xml
     * file.
     *
     * @throws SQLException       if connection cannot be established.
     * @throws LiquibaseException if db-impl or update fails.
     */
    public void update () throws SQLException, LiquibaseException {
        if (!shouldUpdate) {
            System.out.println("Skipping liquibase update due to property  ::liquibase.should.run.update:: == false");
            return;
        }

        // Get JDBC connection
        final var con = dataSource.getConnection();
        final var jdbcCon = new JdbcConnection(con);

        // Initialize Liquibase
        final Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(jdbcCon);
        final Liquibase liquibase = new Liquibase("liquibase/db.changelog-master.xml",
                new ClassLoaderResourceAccessor(),
                database);

        // Perform DB update
        liquibase.update(new Contexts(), new LabelExpression());

    }
}
