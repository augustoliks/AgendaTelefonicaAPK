package com.br.agenda.api.model;

import java.io.Serializable;

public class Contact implements Serializable {
    private Long id;
    private String name;
    private String address;
    private int rating;
    private String site;
    private String telephoneNumber;
    private String pathRelativePhoto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String getPathRelativePhoto() {
        return pathRelativePhoto;
    }

    public void setPathRelativePhoto(String pathRelativePhoto) {
        this.pathRelativePhoto = pathRelativePhoto;
    }
}
