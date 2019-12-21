package net.hassannazar.application;

import liquibase.exception.LiquibaseException;
import net.hassannazar.application.liquibase.LiquibaseUpdater;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import java.sql.SQLException;

@Startup
@Singleton
@TransactionManagement(TransactionManagementType.BEAN)
public class Application {

    @Inject
    LiquibaseUpdater liquibaseUpdater;

    @PostConstruct
    public void initialize () {
        try {
            // Update database using liquibase
            liquibaseUpdater.update();
        } catch (SQLException | LiquibaseException e) {
            e.printStackTrace();
        }
    }

}
