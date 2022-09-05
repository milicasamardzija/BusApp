package com.example.demo.controller.business;

import com.example.demo.dto.business.*;
import com.example.demo.enums.DaysOfWeek;
import com.example.demo.model.business.ActiveDeparture;
import com.example.demo.model.business.BusDeparture;
import com.example.demo.model.business.DrivingLine;
import com.example.demo.service.business.DrivingLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "drivingLine")
public class DrivingLineController {

    @Autowired
    private DrivingLineService drivingLineService;

    @GetMapping
    public ResponseEntity<List<DrivingLineResponse>> getAll(){
        List<DrivingLineResponse> ret = new ArrayList<>();
        for (DrivingLine drivingLine: this.drivingLineService.getAll()
             ) {
            ret.add(new DrivingLineResponse(drivingLine.getId(), drivingLine.getName(), drivingLine.getActiveDepartures(), drivingLine.getBusDepartures()));
        }
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DrivingLineUpdateResponse> getById(@PathVariable int id){
        DrivingLine drivingLine = this.drivingLineService.getById(id);
        return new ResponseEntity<>(new DrivingLineUpdateResponse(drivingLine.getId(), drivingLine.getName(), drivingLine.getDateStart(), drivingLine.getDateEnd(), getDays(drivingLine.getActiveDepartures()), drivingLine.getBus().getId(), this.getDepartures(drivingLine.getBusDepartures())), HttpStatus.OK);
    }

    private List<DaysOfWeek> getDays(List<ActiveDeparture> activeDepartures) {
        List<DaysOfWeek> days = new ArrayList<>();
        for (ActiveDeparture activeDeparture: activeDepartures
             ) {
            days.add(activeDeparture.getDayOfWeek());
        }
        return days;
    }

    private List<BusDepartureResponse> getDepartures(List<BusDeparture> busDepartures) {
        List<BusDepartureResponse> departures = new ArrayList<>();
        for (BusDeparture busDeparture: busDepartures
             ) {
            departures.add(new BusDepartureResponse(busDeparture.getId(), busDeparture.getCity(), busDeparture.getKm(), busDeparture.getTime()));
        }
        return departures;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addDrivingLine(@RequestBody DrivingLineRequest drivingLineRequest){
        this.drivingLineService.addDrivingLine(drivingLineRequest);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> changeDrivingLine(@RequestBody DrivingLineRequest drivingLineRequest){
        this.drivingLineService.changeDrivingLine(drivingLineRequest);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id){
        this.drivingLineService.deleteById(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @PostMapping(value = "/search")
    public ResponseEntity<List<BusDepartureSearchResponse>> searchDrivingLines(@RequestBody BusDepartureSearchRequest busDepartureSearchRequest){
        List<BusDepartureSearchResponse> ret = this.drivingLineService.searchDrivingLines(busDepartureSearchRequest);
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

}
