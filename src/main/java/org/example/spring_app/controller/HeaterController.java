package org.example.spring_app.controller;


import org.example.spring_app.dao.HeaterDao;
import org.example.spring_app.dao.RoomDao;
import org.example.spring_app.dto.HeaterDto;
import org.example.spring_app.model.Heater;
import org.example.spring_app.model.HeaterStatus;
import org.example.spring_app.model.Room;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = { "http://localhost:3000" }, maxAge = 3600)
@RequestMapping("/api/heaters")
@Transactional
public class HeaterController {

    private final HeaterDao heaterDao;
    private final RoomDao roomDao;

    public HeaterController(HeaterDao heaterDao, RoomDao roomDao) {
        this.heaterDao = heaterDao;
        this.roomDao = roomDao;
    }
    @Secured("ROLE_ADMIN")
    @GetMapping
    public List<HeaterDto> findAll() {
        return heaterDao.findAll().stream().map(HeaterDto::new).collect(Collectors.toList());
    }
    @Secured("ROLE_ADMIN")
    @PostMapping
    public HeaterDto create(@RequestBody HeaterDto dto) {
        Room room = roomDao.getReferenceById(dto.getRoomId());
        Heater heater = null;
        // On creation id is not defined
        if (dto.getId() == null) {
            heater = heaterDao.save(new Heater(dto.getName(), dto.getPower(),room, dto.getHeaterStatus()));
        }
        else {
            heater = heaterDao.getReferenceById(dto.getId());
            heater.setHeater_status(dto.getHeaterStatus());
        }
        return new HeaterDto(heater);
    }
    @Secured("ROLE_ADMIN")

    @PutMapping(path = "/{id}/switch")
    public HeaterDto switchStatus(@PathVariable Long id) {
        Heater heater = heaterDao.findById(id).orElseThrow(IllegalArgumentException::new);
        heater.setHeater_status(heater.getHeater_status() == HeaterStatus.ON ? HeaterStatus.OFF: HeaterStatus.ON);
        return new HeaterDto(heater);
    }
    @Secured("ROLE_ADMIN")
    @GetMapping(path = "/{id}")
    public HeaterDto findById(@PathVariable Long id) {
        return heaterDao.findById(id).map(HeaterDto::new).orElse(null);
    }

    @Secured("ROLE_ADMIN")

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        heaterDao.deleteById(id);
    }
}
