package com.example.demo.service.business;

import com.example.demo.dto.business.BusDepartureRequest;
import com.example.demo.dto.business.BusDepartureSearchRequest;
import com.example.demo.dto.business.BusDepartureSearchResponse;
import com.example.demo.dto.business.DrivingLineRequest;
import com.example.demo.enums.DaysOfWeek;
import com.example.demo.model.business.ActiveDeparture;
import com.example.demo.model.business.Bus;
import com.example.demo.model.business.BusDeparture;
import com.example.demo.model.business.DrivingLine;
import com.example.demo.repository.busineess.DrivingLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DrivingLineService {

    @Autowired
    private DrivingLineRepository drivingLineRepository;
    @Autowired
    private BusService busService;
    @Autowired
    private BusDepartureService busDepartureService;
    @Autowired
    private ActiveDepartureService activeDepartureService;

    public List<DrivingLine> getAll() {
        return this.drivingLineRepository.findAll();
    }

    public void addDrivingLine(DrivingLineRequest drivingLineRequest) {
        DrivingLine drivingLine = new DrivingLine();
        drivingLine.setName(drivingLineRequest.name);
        drivingLine.setDateStart(drivingLineRequest.dateStart);
        drivingLine.setDateEnd(drivingLineRequest.dateEnd);

        Bus bus = this.busService.findByIdWithDrivingLines(drivingLineRequest.busId);
        drivingLine.setBus(bus);

        List<ActiveDeparture> activeDepartures = new ArrayList<>();
        for (DaysOfWeek day: drivingLineRequest.daysOfWeek) {
            activeDepartures.add(new ActiveDeparture(day,bus.getSeatNumber()));
        }
        drivingLine.setActiveDepartures(activeDepartures);

        List<BusDeparture> busDepartures = new ArrayList<>();
        for (BusDepartureRequest busDepartureRequest: drivingLineRequest.busDepartures) {
            busDepartures.add(new BusDeparture(busDepartureRequest.city, busDepartureRequest.km, busDepartureRequest.time));
        }
        drivingLine.setBusDepartures(busDepartures);

        this.drivingLineRepository.save(drivingLine);

        bus.getDrivingLines().add(drivingLine);
        this.busService.update(bus);

        for (ActiveDeparture activeDeparture: drivingLine.getActiveDepartures()) {
            activeDeparture.setDrivingLine(drivingLine);
            activeDepartureService.update(activeDeparture);
        }

        for (BusDeparture busDeparture: drivingLine.getBusDepartures()){
            busDeparture.setDrivingLine(drivingLine);
            this.busDepartureService.update(busDeparture);
        }
    }

    public List<BusDepartureSearchResponse> searchDrivingLines(BusDepartureSearchRequest busDepartureSearchRequest) {
        List<BusDepartureSearchResponse> ret = new ArrayList<>();
        int inxStart = 0;
        int inxEnd = 0;
        int activeDepartureId = 0;
        double kmStart = 0;
        double kmEnd = 0;
        for (DrivingLine drivingLine: this.drivingLineRepository.getAll(busDepartureSearchRequest.day)) {
            activeDepartureId = activeDepartureService.findByDrivingLine(drivingLine.getId(), busDepartureSearchRequest.day);
            for (BusDeparture busDeparture: drivingLine.getBusDepartures()) {
                if (busDeparture.getCity().equals(busDepartureSearchRequest.cityStart)){
                    inxStart = drivingLine.getBusDepartures().indexOf(busDeparture);
                    kmStart = busDeparture.getKm();
                }
                if (busDeparture.getCity().equals(busDepartureSearchRequest.cityEnd)){
                    inxEnd = drivingLine.getBusDepartures().indexOf(busDeparture);
                    kmEnd = busDeparture.getKm();
                }
            }
            if (inxStart < inxEnd){
                ret.add(new BusDepartureSearchResponse(drivingLine.getId(), activeDepartureId, busDepartureSearchRequest.cityStart, busDepartureSearchRequest.cityEnd,new Date(), new Date(), kmEnd - kmStart));
            }
        }

        return ret;
    }
}
