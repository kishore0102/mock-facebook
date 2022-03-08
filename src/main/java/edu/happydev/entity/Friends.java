package edu.happydev.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "friends")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Friends {

    @EmbeddedId
    private FriendsId id;

    @Column(name = "status")
    private String status;

    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FriendsId implements Serializable {

        @Column(name = "friend1")
        private String friend1;

        @Column(name = "friend2")
        private String friend2;

    }

}
