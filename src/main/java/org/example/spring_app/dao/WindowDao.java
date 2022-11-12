package org.example.spring_app.dao;

import org.example.spring_app.model.Window;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WindowDao extends JpaRepository<Window, Long>, WindowDaoCustom {
        @Query("select w from Window w where w.id=:l")
        Window getReferenceById(@Param("l") Long l);

        @Query("select w from Window w where w.room.id=:id and w.window_status=org.example.spring_app.model.WindowStatus.OPEN")
        List<Window> findRoomOpenWindows(@Param("id") Long id);

        @Modifying
        @Query("delete from Window w where w.room.id=:id")
        void deleteByRoom(@Param("id") long id);
}
