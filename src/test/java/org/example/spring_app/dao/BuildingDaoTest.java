package org.example.spring_app.dao;

import org.assertj.core.api.Assertions;
import org.example.spring_app.model.Building;
import org.example.spring_app.model.Heater;
import org.example.spring_app.model.Window;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;


@ExtendWith(SpringExtension.class)

@DataJpaTest
class BuildingDaoTest {


    @Autowired
    private BuildingDao buildingDao;


    @Test
    public void shouldFindBuilding() {
        Building building = buildingDao.getReferenceById(-10L);
        Assertions.assertThat(building.getName()).isEqualTo("EF");
    }

    @Test
    public void shouldFindWindows() {
        List<Window> windows = buildingDao.findBuildingWindows(-10L);
        Assertions.assertThat(windows).hasSize(4).extracting("id").containsExactly(-10L, -9L, -8L, -7L);
    }

    @Test
    public void shouldFindHeaters() {
        List<Heater> heaters = buildingDao.findBuildingHeaters(-10L);
        Assertions.assertThat(heaters).hasSize(2).extracting("id").containsExactly(-10L, -9L);
    }
}
