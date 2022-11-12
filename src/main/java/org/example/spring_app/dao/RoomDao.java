package org.example.spring_app.dao;

import org.example.spring_app.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomDao extends JpaRepository<Room, Long> {
}
