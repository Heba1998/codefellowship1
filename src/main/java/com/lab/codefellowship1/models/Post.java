package com.lab.codefellowship1.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String body;
    String createdAt;
    @ManyToOne
    ApplicationUser Uusers;

    public ApplicationUser getOwner() {
        return Uusers;
    }

    public Post(){}

    public long getId() {
        return id;
    }

    public Post(String body, ApplicationUser Uusers){
        this.body = body;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        this.createdAt = sdf.format(new Timestamp(System.currentTimeMillis()).getTime());
        this.Uusers = Uusers;
    }

    public String getBody() {
        return body;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
