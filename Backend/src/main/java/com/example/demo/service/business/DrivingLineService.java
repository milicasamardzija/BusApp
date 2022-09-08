package com.example.demo.service.business;

import com.example.demo.dto.business.BusDepartureRequest;
import com.example.demo.dto.business.BusDepartureSearchRequest;
import com.example.demo.dto.business.BusDepartureSearchResponse;
import com.example.demo.dto.business.DrivingLineRequest;
import com.example.demo.enums.DaysOfWeek;
import com.example.demo.model.business.*;
import com.example.demo.model.users.User;
import com.example.demo.model.users.client.Passenger;
import com.example.demo.repository.busineess.DrivingLineRepository;
import com.example.demo.service.users.client.PassengerService;
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
    @Autowired
    private PassengerService passengerService;

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

       /*for (DaysOfWeek day: drivingLineRequest.daysOfWeek) {
            activeDepartures.add(new ActiveDeparture(null ,day,bus.getSeatNumber()));
        }*/
        List<ActiveDeparture> activeDepartures = this.setDateToActiveDepartures(drivingLineRequest.dateStart, drivingLineRequest.dateEnd, drivingLineRequest.daysOfWeek, bus.getSeatNumber());
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
            activeDepartureService.save(activeDeparture);
        }

        for (BusDeparture busDeparture: drivingLine.getBusDepartures()){
            busDeparture.setDrivingLine(drivingLine);
            this.busDepartureService.save(busDeparture);
        }
    }

    private List<ActiveDeparture> setDateToActiveDepartures(Date dateStart, Date dateEnd, List<DaysOfWeek> daysOfWeek, int seatNumber) {
        List<ActiveDeparture> activeDepartures = new ArrayList<>();
        List<Date> days = new ArrayList<>();
        days.add(new Date(dateStart.getTime()));
        while (days.get(days.size() - 1).compareTo(dateEnd) != 0){
            int index = days.size() - 1;
            Date d = days.get(index);
            Date d1 = new Date(d.getTime());
            d1.setDate(d1.getDate() + 1);
            days.add(new Date(d1.getTime()));
        }

        for (Date d1 : days) {
            if (daysOfWeek.contains(DaysOfWeek.values()[d1.getDay()]))
                activeDepartures.add(new ActiveDeparture(d1, DaysOfWeek.values()[d1.getDay()],seatNumber));
        }
        return  activeDepartures;
    }

    public List<BusDepartureSearchResponse> searchDrivingLines(BusDepartureSearchRequest busDepartureSearchRequest, User user) {
        Price price = this.priceService.getValidPrice();
        Passenger passenger = this.passengerService.findById(user.getId());
        Discount discount = new Discount();

        if (passenger.getDiscount() != null)
            discount = passenger.getDiscount();

        List<BusDepartureSearchResponse> ret = new ArrayList<>();
        int inxStart = 0;
        int inxEnd = 0;
        ActiveDeparture activeDeparture = null;
        double kmStart = 0;
        double kmEnd = 0;
        String timeStart = "";
        String timeEnd = "";
        busDepartureSearchRequest.date.setHours(2);
        busDepartureSearchRequest.date.setMinutes(0);
        for (DrivingLine drivingLine: this.drivingLineRepository.getAll(DaysOfWeek.values()[busDepartureSearchRequest.date.getDay()])) {
            activeDeparture = activeDepartureService.findByDrivingLine(drivingLine.getId(), DaysOfWeek.values()[busDepartureSearchRequest.date.getDay()], busDepartureSearchRequest.date);
            for (BusDeparture busDeparture: drivingLine.getBusDepartures()) {
                if (busDeparture.getCity().equalsIgnoreCase(busDepartureSearchRequest.cityStart)){
                    inxStart = drivingLine.getBusDepartures().indexOf(busDeparture);
                    kmStart = busDeparture.getKm();
                    timeStart = busDeparture.getTime();
                }
                if (busDeparture.getCity().equalsIgnoreCase(busDepartureSearchRequest.cityEnd)){
                    inxEnd = drivingLine.getBusDepartures().indexOf(busDeparture);
                    kmEnd = busDeparture.getKm();
                    timeEnd = busDeparture.getTime();
                }
            }
            if (inxStart < inxEnd){
                ret.add(new BusDepartureSearchResponse(drivingLine.getId(), activeDeparture.getId(), busDepartureSearchRequest.cityStart.toUpperCase(), busDepartureSearchRequest.cityEnd.toUpperCase(),timeStart, timeEnd, price.getPricePerKilometer() * Math.abs(kmEnd - kmStart) * ((100 - discount.getPercentage()) / 100) , price.getPricePerKilometerMonthlyTicket() * Math.abs(kmEnd - kmStart) * 20 * ((100 - discount.getPercentage()) / 100), activeDeparture.getSeats(), price.getPricePerKilometer() * Math.abs(kmEnd - kmStart), discount.getPercentage()));
            }
        }

        return ret;
    }


    public void changeDrivingLine(DrivingLineRequest drivingLineRequest) {
        DrivingLine drivingLine = this.drivingLineRepository.findCompleteById(drivingLineRequest.id);
        drivingLine.setName(drivingLineRequest.name);
        drivingLine.setDateStart(drivingLineRequest.dateStart);
        drivingLine.setDateEnd(drivingLineRequest.dateEnd);

        Bus bus = this.busService.findByIdWithDrivingLines(drivingLineRequest.busId);
        drivingLine.setBus(bus);
        Bus busOld = this.busService.findByIdWithDrivingLines(drivingLine.getBus().getId());

        //this.drivingLineRepository.save(drivingLine);

        for (BusDeparture busDeparture: drivingLine.getBusDepartures()
             ) {
            busDeparture.setDrivingLine(null);
            this.busDepartureService.delete(busDeparture);
        }
        drivingLine.setBusDepartures(null);

        for (ActiveDeparture activeDeparture: drivingLine.getActiveDepartures()
             ) {
            activeDeparture.setDrivingLine(null);
            this.activeDepartureService.delete(activeDeparture);
        }
        drivingLine.setActiveDepartures(null);

      /*  List<ActiveDeparture> activeDepartures = new ArrayList<>();
        for (DaysOfWeek day: drivingLineRequest.daysOfWeek) {
            activeDepartures.add(new ActiveDeparture(day,bus.getSeatNumber()));
        }
        drivingLine.setActiveDepartures(activeDepartures);*/

        List<BusDeparture> busDepartures = new ArrayList<>();
        for (BusDepartureRequest busDepartureRequest: drivingLineRequest.busDepartures) {
            busDepartures.add(new BusDeparture(busDepartureRequest.city, busDepartureRequest.km, busDepartureRequest.time));
        }
        drivingLine.setBusDepartures(busDepartures);

        this.drivingLineRepository.save(drivingLine);

        List<DrivingLine> drivingLines = busOld.getDrivingLines();
        if (drivingLines.size() > 0)
            drivingLines.remove(drivingLine);
        busOld.setDrivingLines(drivingLines);
        this.busService.save(busOld);
        bus.getDrivingLines().add(drivingLine);
        this.busService.save(bus);

        for (ActiveDeparture activeDeparture: drivingLine.getActiveDepartures()) {
            activeDeparture.setDrivingLine(drivingLine);
            activeDepartureService.save(activeDeparture);
        }

        for (BusDeparture busDeparture: drivingLine.getBusDepartures()){
            busDeparture.setDrivingLine(drivingLine);
            this.busDepartureService.save(busDeparture);
        }

    }

    public void deleteById(int id) {
        this.drivingLineRepository.deleteById(id);
    }

    public DrivingLine getById(int id) {
        return this.drivingLineRepository.findCompleteById(id);
    }
}
