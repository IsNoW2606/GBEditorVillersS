package org.helmo.gbeditor.model;

public class Author {
    private final String matricule;
    private String firstName;
    private String lastName;

    public Author(String matricule) {
        this.matricule = matricule;
    }

    public void setName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
