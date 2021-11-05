package com.soa.soa_api.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Station {

    @Id
    private Long id;

    @NotBlank
    public float locationX;
    @NotBlank
    public float locationY;

    public String name;
    public String link;
    public String standardname;

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public float getLocationX() {
        return locationX;
    }

    public void setLocationX(float locationX) {
        this.locationX = locationX;
    }

    public float getLocationY() {
        return locationY;
    }

    public void setLocationY(float locationY) {
        this.locationY = locationY;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getStandardname() {
        return standardname;
    }

    public void setStandardname(String standardname) {
        this.standardname = standardname;
    }
}
