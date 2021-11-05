package com.soa.soa_api.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class User {

    public User(String name, String password){
        setName(name);
        setPassword(password);
        setJoinDate(LocalDateTime.now());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "user.error.name")
    private String name;

    @NotBlank(message = "user.error.passwd")
    private String password;

    private LocalDateTime joinDate;

    private LocalDateTime lastSeenDate;

    @ElementCollection
    private Set<Long> favoriteStations;

    public User(){
        setJoinDate(LocalDateTime.now());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getJoinDate() {
        return joinDate;
    }

    private void setJoinDate(LocalDateTime joinDate){
        this.joinDate = joinDate;
    }

    public LocalDateTime getLastSeenDate() {
        return lastSeenDate;
    }

    public void setLastSeenDate(LocalDateTime lastSeenDate) {
        this.lastSeenDate = lastSeenDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Long> getFavoriteStations(){
        return favoriteStations;
    }

    public void addFavoriteStation(Long stationID){
        if (!favoriteStations.contains(stationID))
            favoriteStations.add(stationID);
    }

    public void removeFavoriteStation(Long stationID){
        if (favoriteStations.contains(stationID))
            favoriteStations.remove(stationID);
    }


}
