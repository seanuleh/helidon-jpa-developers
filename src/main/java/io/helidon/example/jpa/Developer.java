package io.helidon.example.jpa;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// import org.eclipse.persistence.annotations.UuidGenerator;

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
    private ZonedDateTime createdAt;

    // @ElementCollection
    // private ArrayList<String> skills;

    @Deprecated
    protected Developer() { 
        super();
    }

    public String getId() {
        return this.id.toString();
    }

    public String getName() {
        return this.name;
    }

    public String getTeam() {
        return this.team;
    }

    public ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    // public ArrayList<String> getSkills() {
    //     return this.skills;
    // }

    public void setId(String id) {
        this.id = Objects.requireNonNull(id);
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public void setTeam(String team) {
        this.team = Objects.requireNonNull(team);
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = Objects.requireNonNull(createdAt);
    }

    @Override
    public String toString() {
        return this.getId() + " - " + this.getName() + " - " + this.getTeam();
    }

}
