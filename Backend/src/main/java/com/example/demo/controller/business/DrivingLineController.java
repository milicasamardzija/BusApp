package com.example.demo.controller.business;

import com.example.demo.dto.business.BusDepartureSearchRequest;
import com.example.demo.dto.business.BusDepartureSearchResponse;
import com.example.demo.dto.business.DrivingLineRequest;
import com.example.demo.model.business.DrivingLine;
import com.example.demo.service.business.DrivingLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "drivingLine")
public class DrivingLineController {

    @Autowired
    private DrivingLineService drivingLineService;

    @GetMapping
    public ResponseEntity<List<DrivingLine>> getAll(){
        return new ResponseEntity<>(this.drivingLineService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addDrivingLine(@RequestBody DrivingLineRequest drivingLineRequest){
        this.drivingLineService.addDrivingLine(drivingLineRequest);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> changeBus(@RequestBody DrivingLineRequest bus){

        return new ResponseEntity<>( HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id){

        return new ResponseEntity<>( HttpStatus.OK);
    }

    @PostMapping(value = "/search")
    public ResponseEntity<List<BusDepartureSearchResponse>> searchDrivingLines(@RequestBody BusDepartureSearchRequest busDepartureSearchRequest){
        List<BusDepartureSearchResponse> ret = this.drivingLineService.searchDrivingLines(busDepartureSearchRequest);
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

}
