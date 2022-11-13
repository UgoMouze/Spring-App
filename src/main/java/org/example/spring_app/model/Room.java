package org.example.spring_app.model;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Set;

@Entity
public class Room {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable=false)
    private Integer floor;

    @Column(nullable=false)
    private String name;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Building building;
    private Double current_temperature;
    private Double target_temperature;


    @OneToMany(mappedBy = "room")
    private Set<Heater> heaters;

    @OneToMany(mappedBy = "room")
    private Set<Window> windows;

    public Room(){
    }
    public Room(Long id, Integer floor, String name, Double current_temperature, Double target_temperature, Set<Heater> heaters, Set<Window> windows, Building building ) {
        this.id = id;
        this.floor = floor;
        this.name = name;
        this.current_temperature = current_temperature;
        this.target_temperature = target_temperature;
        this.heaters = heaters;
        this.windows = windows;
        this.building = building;
    }

    public Room(String name, Long id, Building building){
        this.id = id;
        this.name = name;
        this.building= building;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Double getCurrent_temperature() {
        return current_temperature;
    }

    public void setCurrent_temperature(Double current_temperature) {
        this.current_temperature = current_temperature;
    }

    public Double getTarget_temperature() {
        return target_temperature;
    }

    public void setTarget_temperature(Double target_temperature) {
        this.target_temperature = target_temperature;
    }

    public Set<Heater> getHeaters() {
        return heaters;
    }

    public void setHeaters(Set<Heater> heaters) {
        this.heaters = heaters;
    }

    public Set<Window> getWindows() {
        return windows;
    }

    public void setWindows(Set<Window> windows) {
        this.windows = windows;
    }

}
