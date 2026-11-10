
package com.airtribe.learntrack.entity;

public class Student extends Person {

    private String batch;
    private boolean active;

    // Constructor with email
    public Student(int id, String firstName, String lastName,
                   String email, String batch) {

        super(id, firstName, lastName, email);

        this.batch = batch;
        this.active = true;
    }

    // Constructor without email (constructor overloading)
    public Student(int id, String firstName, String lastName,
                   String batch) {

        super(id, firstName, lastName, null);

        this.batch = batch;
        this.active = true;
    }

    // Getter and Setter for batch
    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    // Getter and Setter for active
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    // Method overriding
    @Override
    public String getDisplayName() {
        return "Student: " + getFirstName() + " " + getLastName();
    }
}
