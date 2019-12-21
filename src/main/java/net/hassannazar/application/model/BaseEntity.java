package net.hassannazar.application.model;

import javax.persistence.Id;
import java.time.LocalDateTime;

public class BaseEntity {

    @Id
    private long id;
    private LocalDateTime created;
    private LocalDateTime dateModified;
    private String modifier;

    public BaseEntity (LocalDateTime dateModified, String modifier) {
        this.created = LocalDateTime.now();
        this.dateModified = dateModified;
        this.modifier = modifier;
    }

    public long getId () {
        return id;
    }

    public void setId (long id) {
        this.id = id;
    }

    public LocalDateTime getCreated () {
        return created;
    }

    public void setCreated (LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getDateModified () {
        return dateModified;
    }

    public void setDateModified (LocalDateTime dateModified) {
        this.dateModified = dateModified;
    }

    public String getModifier () {
        return modifier;
    }

    public void setModifier (String modifier) {
        this.modifier = modifier;
    }
}
