package org.example.spring_app.dao;

import org.example.spring_app.model.Heater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HeaterDao extends JpaRepository<Heater, Long> {

    @Modifying
    @Query("delete from Heater h where h.room.id=:id")
    void deleteByRoom(@Param("id") long id);
}
