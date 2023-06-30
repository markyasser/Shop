package com.testing.test_app.model;

import java.util.Arrays;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "Post")
public class Post {

    @MongoId
    private String id;
    private String profile;
    private String description;
    private int exp;
    private String[] techs;

    public Post() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public String[] getTechs() {
        return techs;
    }

    public void setTechs(String[] techs) {
        this.techs = techs;
    }

    @Override
    public String toString() {
        return "Post {" +
                "id='" + id +
                ", profile='" + profile + '\'' +
                ", description='" + description + '\'' +
                ", exp=" + exp +
                ", techs=" + Arrays.toString(techs) +
                "}";
    }
}
