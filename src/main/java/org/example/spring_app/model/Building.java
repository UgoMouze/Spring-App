package org.example.spring_app.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Building {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable=false)
    private String name;


    @OneToMany(mappedBy = "building")
    private Set<Room> rooms;


    public Building(){
    }

    public Building(Long id, String name, Set<Room> rooms) {
        this.id = id;
        this.name = name;
        this.rooms = rooms;
    }

    public Building(String name, Long id){
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Set<Room> getRoom() {
        return this.rooms;
    }

    public Long getId() {
        return this.id;
    }
}
