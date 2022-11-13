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

    public String getName() {
        return this.name;
    }

    public Double getCurrentTemperature() {
        return this.current_temperature;
    }

    public Set<Window> getWindows() {
        return this.windows;
    }

    public Set<Heater> getHeaters() {
        return this.heaters;
    }

    public Long getId() {
        return this.id;
    }

    public Integer getFloor(){return this.floor;}
    public Building getBuilding(){ return this.building; }
}
