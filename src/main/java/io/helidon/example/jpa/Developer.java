package io.helidon.example.jpa;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Objects;

import javax.persistence.*;

@Access(value = AccessType.FIELD) 
@Entity(name = "Developer") 
@Table(name = "DEVELOPER")
public class Developer implements Serializable { 

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="ID", updatable = false, nullable = false)
    private String id;

    @Basic(optional = true) 
    @Column(insertable = true, name = "NAME", nullable = false, updatable = true)
    private String name;

    @Basic(optional = true) 
    @Column(insertable = true, name = "TEAM", nullable = false, updatable = true)
    private String team;

    @Basic(optional = true) 
    @Column(insertable = true, name = "CREATED_AT", nullable = false, updatable = true)
    private OffsetDateTime createdAt;

    @Basic(optional = true)
    @Column(insertable = true, name = "UPDATED_AT", nullable = false, updatable = true)
    private OffsetDateTime updatedAt;


    @Convert(converter = DeveloperSkillsConverter.class)
    @Column(insertable = true, name = "SKILLS", nullable = false, updatable = true)
    private ArrayList<String> skils;

    @Deprecated
    protected Developer() { 
        super();
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getTeam() {
        return this.team;
    }

    public OffsetDateTime getCreatedAt() {
        return this.createdAt;
    }

    public ArrayList<String> getSkils() {
         return this.skils;
    }

    public void setId(String id) {
        this.id = Objects.requireNonNull(id);
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public void setTeam(String team) {
        this.team = Objects.requireNonNull(team);
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = Objects.requireNonNull(createdAt);
    }

    public void setSkils(ArrayList<String> skils)
    {
        this.skils = skils;
    }

    @Override
    public String toString() {
        return this.getId() + " - " + this.getName() + " - " + this.getTeam();
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = OffsetDateTime.now();
        this.updatedAt = OffsetDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = OffsetDateTime.now();
    }
}
