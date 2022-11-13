package org.example.spring_app.dao;

import org.assertj.core.api.Assertions;
import org.example.spring_app.model.Room;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)

@DataJpaTest
class RoomDaoTest {

    @Autowired
    private RoomDao roomDao;

    @Test
    public void shouldFindARoom() {
        Room room = roomDao.getReferenceById(-10L);
        Assertions.assertThat(room.getName()).isEqualTo("Room1");
        Assertions.assertThat(room.getCurrent_temperature()).isEqualTo(22.3);
    }
}
