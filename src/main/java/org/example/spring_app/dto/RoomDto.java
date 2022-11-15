package org.example.spring_app.dto;

import org.example.spring_app.model.Heater;
import org.example.spring_app.model.Room;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RoomDto {
    private Long id;
    private String name;
    private Integer floor;
    private String buildingName;
    private Long buildingId;
    private Double current_temperature;
    private Double target_temperature;
    private Set<Long> heaterIds;
    private Set<Long> windowIds;

    public RoomDto() {
    }

    public RoomDto(Room room) {
        this.id = room.getId();
        this.name = room.getName();
        this.floor = room.getFloor();
        this.buildingName = room.getBuilding().getName();
        this.buildingId = room.getBuilding().getId();
        this.current_temperature = room.getCurrent_temperature();
        this.target_temperature = room.getTarget_temperature();
        this.heaterIds = new HashSet<Long>();
        if(room.getHeaters() != null ) room.getHeaters().forEach((heater) -> heaterIds.add(heater.getId()));
        this.windowIds = new HashSet<Long>();
        if(room.getWindows() != null ) room.getWindows().forEach((window)-> windowIds.add(window.getId()));
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getFloor() {
        return floor;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public Double getCurrent_temperature() {
        return current_temperature;
    }

    public Double getTarget_temperature() {
        return target_temperature;
    }

    public Set<Long> getHeaterIds() {
        return heaterIds;
    }

    public Set<Long> getWindowIds() {
        return windowIds;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public void setCurrent_temperature(Double current_temperature) {
        this.current_temperature = current_temperature;
    }

    public void setTarget_temperature(Double target_temperature) {
        this.target_temperature = target_temperature;
    }

    public void setHeaterIds(Set<Long> heaterIds) {
        this.heaterIds = heaterIds;
    }

    public void setWindowIds(Set<Long> windowIds) {
        this.windowIds = windowIds;
    }
}
