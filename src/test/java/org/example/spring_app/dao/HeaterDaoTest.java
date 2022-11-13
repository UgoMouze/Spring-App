package org.example.spring_app.dao;

import org.assertj.core.api.Assertions;
import org.example.spring_app.model.Heater;
import org.example.spring_app.model.HeaterStatus;
import org.example.spring_app.model.Room;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@ExtendWith(SpringExtension.class)

@DataJpaTest
class HeaterDaoTest {

    @Autowired
    private HeaterDao heaterDao;

    @Autowired
    private RoomDao roomDao;

    @Test
    public void shouldFindAHeater() {
        Heater heater = heaterDao.getReferenceById(-10L);
        Assertions.assertThat(heater.getName()).isEqualTo("Heater1");
        Assertions.assertThat(heater.getHeater_status()).isEqualTo(HeaterStatus.ON);
    }

    @Test
    public void souldDeletedHeaters(){
        Room room = roomDao.getReferenceById(-10L);
        Set<Heater> heaters = room.getHeaters();
        List<Long> heaterIds = heaters.stream().map(h -> h.getId()).collect(Collectors.toList());
        Assertions.assertThat(heaterIds.size()).isEqualTo(2);

        heaterDao.deleteByRoom(-10L);
        List<Heater> result = heaterDao.findAllById(heaterIds);
        Assertions.assertThat(result).isEmpty();
    }
}
