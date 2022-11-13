package org.example.spring_app.dao;

import org.example.spring_app.model.Building;
import org.example.spring_app.model.Heater;
import org.example.spring_app.model.Window;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BuildingDao extends JpaRepository<Building, Long> {

    @Query("select h from Heater h where h.room.building.id=:id")
    List<Heater> findBuildingHeaters(@Param("id") Long id);

    @Query("select w from Window w where w.room.building.id=:id")
    List<Window> findBuildingWindows(@Param("id") Long id);
}
