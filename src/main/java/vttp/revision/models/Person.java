package vttp.revision.models;

import java.util.UUID;

public class Person {
    private final String id;
    private String firstName;
    private String lastName;

    public Person(String fName, String lName) {
        this.id = UUID.randomUUID().toString().substring(0, 8);
        this.firstName = fName;
        this.lastName = lName;
    }

    // Set ID
    public Person(String id) {     this.id = id;    }

    public String getId() {     return id;  }

    public String getFirstName() {  return firstName;   }
    public void setFirstName(String firstName) {    this.firstName = firstName; }

    public String getLastName() {   return lastName;    }
    public void setLastName(String lastName) {  this.lastName = lastName;   }
    
    @Override
    public String toString() {
        return  "ID: " + id + "\nFirst Name: " + firstName + "\nLast Name: " + lastName + "\n";
    }
}
