package org.ejournal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pupil")
public class Pupil {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pupil_id")
    private Long id;

    @Email
    @NotNull
    @Column(name = "email", unique = true)
    private String email;

    @JsonIgnore
    @OneToOne(mappedBy = "pupil", cascade = CascadeType.ALL)
    private Journal journal;

    private String firstName;

    private String lastName;

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String fistName) {
        this.firstName = fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
