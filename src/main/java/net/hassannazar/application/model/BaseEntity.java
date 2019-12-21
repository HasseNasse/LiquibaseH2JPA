package net.hassannazar.application.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Past
    private LocalDateTime created;
    @PastOrPresent
    private LocalDateTime dateModified;
    @NotEmpty
    private String modifier;

    public BaseEntity () {
        this.created = LocalDateTime.now();
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
