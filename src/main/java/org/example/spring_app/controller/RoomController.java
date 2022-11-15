package org.example.spring_app.controller;

import org.example.spring_app.dao.BuildingDao;
import org.example.spring_app.dao.HeaterDao;
import org.example.spring_app.dao.RoomDao;
import org.example.spring_app.dao.WindowDao;
import org.example.spring_app.dto.HeaterDto;
import org.example.spring_app.dto.RoomDto;
import org.example.spring_app.dto.WindowDto;
import org.example.spring_app.model.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = { "http://localhost:3000" }, maxAge = 3600)
@RequestMapping("/api/rooms")
@Transactional
public class RoomController {
    private final RoomDao roomDao;
    private final BuildingDao buildingDao;
    private final HeaterDao heaterDao;
    private final WindowDao windowDao;


    public RoomController(RoomDao roomDao, BuildingDao buildingDao, WindowDao windowDao, HeaterDao heaterDao){
        this.roomDao = roomDao;
        this.buildingDao = buildingDao;
        this.windowDao = windowDao;
        this.heaterDao = heaterDao;
    }

    @GetMapping
    public List<RoomDto> findAll(){
        return roomDao.findAll().stream().map(RoomDto::new).collect(Collectors.toList());
    }

    @PostMapping
    public RoomDto create(@RequestBody RoomDto dto){
        Building building = buildingDao.getReferenceById(dto.getBuildingId());
        Room room = null;
        if(dto.getId() == null){
            room = roomDao.save(new Room(dto.getName(), dto.getId(), building));
        }
        else{
            room = roomDao.getReferenceById(dto.getId());
            room.setTarget_temperature(dto.getTarget_temperature());
            room.setCurrent_temperature(dto.getCurrent_temperature());
        }
        return new RoomDto(room);
    }

    @GetMapping(path = "/{id}")
    public RoomDto findById(@PathVariable Long id) {
        return roomDao.findById(id).map(RoomDto::new).orElse(null);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        RoomDto roomdto = roomDao.findById(id).map(RoomDto::new).orElse(null);
        if(roomdto != null){
            if(roomdto.getWindowIds() != null) roomdto.getWindowIds().forEach((idw)->windowDao.deleteById(idw));
            if(roomdto.getHeaterIds() != null) roomdto.getHeaterIds().forEach((idh)->heaterDao.deleteById(idh));
        }
        roomDao.deleteById(id);
    }

    @PutMapping(path = "{id}/switchWindow")
    public RoomDto switchWindows(@PathVariable Long id){
        Room room = roomDao.findById(id).orElseThrow(IllegalArgumentException::new);
        if(room.getWindows() != null) room.getWindows().forEach((w)->w.setWindowStatus(w.getWindowStatus() == WindowStatus.OPEN ? WindowStatus.CLOSED: WindowStatus.OPEN));
        return new RoomDto(room);
    }

    @PutMapping(path = "{id}/switchHeater")
    public RoomDto switchHeaters(@PathVariable Long id){
        Room room = roomDao.findById(id).orElseThrow(IllegalArgumentException::new);
        if(room.getHeaters()!=null)room.getHeaters().forEach((h)->h.setHeater_status(h.getHeater_status() == HeaterStatus.ON ? HeaterStatus.OFF: HeaterStatus.ON));
        return new RoomDto(room);
    }

}
