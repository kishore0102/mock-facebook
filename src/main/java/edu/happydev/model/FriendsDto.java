package edu.happydev.model;

import lombok.Value;

@Value
public class FriendsDto {

    String name;
    String status;
    String bio;
    String email;

    public FriendsDto(String email, String status, String firstName, String lastName, String bio, String friend2) {
        this.name = (firstName + " " + lastName).trim();
        this.status = status.equals("Y") ? "Friends" : (email.equals(friend2) ? "Request sent" : "Pending");
        this.email = email;
        this.bio = bio;
    }

}
