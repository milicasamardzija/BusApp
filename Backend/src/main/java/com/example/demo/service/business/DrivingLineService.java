package com.example.demo.service.business;

import com.example.demo.dto.business.BusDepartureRequest;
import com.example.demo.dto.business.BusDepartureSearchRequest;
import com.example.demo.dto.business.BusDepartureSearchResponse;
import com.example.demo.dto.business.DrivingLineRequest;
import com.example.demo.enums.DaysOfWeek;
import com.example.demo.model.business.*;
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
    @Autowired
    private PriceService priceService;

    public List<DrivingLine> getAll() {
        return this.drivingLineRepository.getAllWithWeeks();
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
        Price price = this.priceService.getById(1);
        List<BusDepartureSearchResponse> ret = new ArrayList<>();
        int inxStart = 0;
        int inxEnd = 0;
        ActiveDeparture activeDeparture = null;
        double kmStart = 0;
        double kmEnd = 0;
        for (DrivingLine drivingLine: this.drivingLineRepository.getAll(busDepartureSearchRequest.day)) {
            activeDeparture = activeDepartureService.findByDrivingLine(drivingLine.getId(), busDepartureSearchRequest.day);
            for (BusDeparture busDeparture: drivingLine.getBusDepartures()) {
                if (busDeparture.getCity().equalsIgnoreCase(busDepartureSearchRequest.cityStart)){
                    inxStart = drivingLine.getBusDepartures().indexOf(busDeparture);
                    kmStart = busDeparture.getKm();
                }
                if (busDeparture.getCity().equalsIgnoreCase(busDepartureSearchRequest.cityEnd)){
                    inxEnd = drivingLine.getBusDepartures().indexOf(busDeparture);
                    kmEnd = busDeparture.getKm();
                }
            }
            if (inxStart < inxEnd){
                ret.add(new BusDepartureSearchResponse(drivingLine.getId(), activeDeparture.getId(), busDepartureSearchRequest.cityStart.toUpperCase(), busDepartureSearchRequest.cityEnd.toUpperCase(),new Date(), new Date(), price.getPricePerKilometer() * Math.abs(kmEnd - kmStart), price.getPricePerKilometerMonthlyTicket() * Math.abs(kmEnd - kmStart) * 20, activeDeparture.getSeats()));
            }
        }

        return ret;
    }

    public void changeDrivingLine(DrivingLineRequest drivingLineRequest) {
        DrivingLine drivingLine = this.drivingLineRepository.findById(drivingLineRequest.id);
        drivingLine.setName(drivingLineRequest.name);
        drivingLine.setDateStart(drivingLineRequest.dateStart);
        drivingLine.setDateEnd(drivingLineRequest.dateEnd);

        Bus bus = this.busService.findByIdWithDrivingLines(drivingLineRequest.busId);
        drivingLine.setBus(bus);

        this.drivingLineRepository.save(drivingLine);

        //sredi
        //drivingLine.getBusDepartures();
        //drivingLine.getActiveDepartures();

    }

    public void deleteById(int id) {
        this.drivingLineRepository.deleteById(id);
    }
}
