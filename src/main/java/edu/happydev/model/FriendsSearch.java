package edu.happydev.model;

import lombok.Value;

@Value
public class FriendsSearch {

    String name;
    String bio;
    String email;
    String status;

    public FriendsSearch(String firstName, String lastName, String bio, String email, String status) {
        this.name = (firstName + " " + lastName).trim();
        this.bio = bio;
        this.email = email;
        this.status = status;
    }

}
