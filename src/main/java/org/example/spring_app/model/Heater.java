package org.example.spring_app.model;


import javax.persistence.*;

@Entity
public class Heater {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable=false)
    private String name;


    private Long power;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Room room;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private HeaterStatus heater_status;


    public Heater(){

    }

    public Heater(String name, Long power, Room room, HeaterStatus status) {
        this.name = name;
        this.power = power;
        this.room = room;
        this.heater_status = status;
    }

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

    public Long getPower() {
        return power;
    }

    public void setPower(Long power) {
        this.power = power;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public HeaterStatus getHeater_status() {
        return heater_status;
    }

    public void setHeater_status(HeaterStatus heater_status) {
        this.heater_status = heater_status;
    }
}
