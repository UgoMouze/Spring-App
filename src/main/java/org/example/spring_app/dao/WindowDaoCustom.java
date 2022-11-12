package org.example.spring_app.dao;

import org.example.spring_app.model.Window;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WindowDaoCustom {
    List<Window> findRoomOpenWindows(Long id);

    List<Window> findWindowsbyRoom(@Param("id") Long id);
}
