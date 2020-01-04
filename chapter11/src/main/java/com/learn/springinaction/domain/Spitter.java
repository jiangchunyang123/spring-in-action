package com.learn.springinaction.domain;


import javax.persistence.*;
import java.util.Objects;

@Entity(name = "spitter")
public class Spitter {

    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String username;
    @Column
    private String password;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Spitter() {

    }

    public Spitter(String firstName, String lastName, String username, String password, Long id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.id = id;
    }

    public Spitter(Long id, String username, String password, String fullName, String email) {

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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Spitter(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Spitter{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spitter spitter = (Spitter) o;
        return Objects.equals(firstName, spitter.firstName) &&
                Objects.equals(lastName, spitter.lastName) &&
                Objects.equals(username, spitter.username) &&
                Objects.equals(password, spitter.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, username, password);
    }
}
