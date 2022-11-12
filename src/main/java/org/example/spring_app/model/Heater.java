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

    public Heater(Long id, String name, Long power, Room room, HeaterStatus status) {
        this.id = id;
        this.name = name;
        this.power = power;
        this.room = room;
        this.heater_status = status;
    }

    public String getName() {
        return this.name;
    }


    public HeaterStatus getHeaterStatus() {
        return this.heater_status;
    }

    public Long getId() {
        return this.id;
    }
}
