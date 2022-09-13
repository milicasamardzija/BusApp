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

import java.util.*;

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
        this.drivingLineRepository.save(drivingLine);

        Bus bus = this.busService.findByIdWithDrivingLines(drivingLineRequest.busId);
        List<DrivingLine> drivingLines = bus.getDrivingLines();
        drivingLines.add(drivingLine);
        bus.setDrivingLines(drivingLines);

        busService.save(bus);
        drivingLine.setBus(bus);

        List<ActiveDeparture> activeDepartures = this.setDateToActiveDepartures(drivingLineRequest.dateStart, drivingLineRequest.dateEnd, drivingLineRequest.daysOfWeek, bus.getSeatNumber());
        drivingLine.setActiveDepartures(activeDepartures);

        List<BusDeparture> busDepartures = new ArrayList<>();
        for (BusDepartureRequest busDepartureRequest: drivingLineRequest.busDepartures) {
            busDepartures.add(new BusDeparture(busDepartureRequest.city, busDepartureRequest.km, busDepartureRequest.time));
        }
        drivingLine.setBusDepartures(busDepartures);

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
        Date d1 = new Date(dateStart.getTime());
        while (!(d1.getMonth() == dateEnd.getMonth() && d1.getDate() == dateEnd.getDate() && d1.getYear() == dateEnd.getYear())){
            d1.setDate(d1.getDate() + 1);
            days.add(new Date(d1.getTime()));
        }

        for (Date d2 : days) {
            if (daysOfWeek.contains(DaysOfWeek.values()[d2.getDay()]))
                activeDepartures.add(new ActiveDeparture(d2, DaysOfWeek.values()[d2.getDay()],seatNumber));
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
        List<ActiveDeparture> activeDepartures = new ArrayList<>();
        double kmStart = 0;
        double kmEnd = 0;
        String timeStart = "";
        String timeEnd = "";
        for (DrivingLine drivingLine: this.drivingLineRepository.getAll(DaysOfWeek.values()[busDepartureSearchRequest.date.getDay()])) {
            activeDepartures = activeDepartureService.findByDrivingLine(drivingLine.getId(), DaysOfWeek.values()[busDepartureSearchRequest.date.getDay()]);
            Date start = new Date(busDepartureSearchRequest.date.getTime());
            start.setHours(0);
            start.setMinutes(1);
            Date end = new Date(busDepartureSearchRequest.date.getTime());
            end.setHours(23);
            end.setMinutes(59);
            for (ActiveDeparture a: activeDepartures
                 ) {
                if (a.getDate().after(start) && a.getDate().before(end))
                    activeDeparture = a;
            }
            
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
                if (user != null)
                    ret.add(new BusDepartureSearchResponse(drivingLine.getId(), activeDeparture.getId(), busDepartureSearchRequest.cityStart.toUpperCase(), busDepartureSearchRequest.cityEnd.toUpperCase(),timeStart, timeEnd, price.getPricePerKilometer() * Math.abs(kmEnd - kmStart) * ((100 - discount.getPercentage()) / 100) , price.getPricePerKilometerMonthlyTicket() * Math.abs(kmEnd - kmStart) * 20 * ((100 - discount.getPercentage()) / 100), activeDeparture.getSeats(), price.getPricePerKilometer() * Math.abs(kmEnd - kmStart), discount.getPercentage()));
                else
                    ret.add(new BusDepartureSearchResponse(drivingLine.getId(), activeDeparture.getId(), busDepartureSearchRequest.cityStart.toUpperCase(), busDepartureSearchRequest.cityEnd.toUpperCase(),timeStart, timeEnd, price.getPricePerKilometer() * Math.abs(kmEnd - kmStart) , price.getPricePerKilometerMonthlyTicket() * Math.abs(kmEnd - kmStart) * 20 , activeDeparture.getSeats(), price.getPricePerKilometer() * Math.abs(kmEnd - kmStart), 0));
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

        for (BusDeparture busDeparture: drivingLine.getBusDepartures()
             ) {
            this.busDepartureService.deleteById(busDeparture.getId());
        }
        drivingLine.setBusDepartures(null);

        for (ActiveDeparture activeDeparture: drivingLine.getActiveDepartures()
             ) {
            this.activeDepartureService.deleteById(activeDeparture.getId());
        }
        drivingLine.setActiveDepartures(null);

        this.drivingLineRepository.save(drivingLine);

        List<BusDeparture> busDepartures = new ArrayList<>();
        for (BusDepartureRequest busDepartureRequest: drivingLineRequest.busDepartures) {
            busDepartures.add(new BusDeparture(busDepartureRequest.city, busDepartureRequest.km, busDepartureRequest.time));
        }
        drivingLine.setBusDepartures(busDepartures);

        List<ActiveDeparture> activeDepartures = this.setDateToActiveDepartures(drivingLineRequest.dateStart, drivingLineRequest.dateEnd, drivingLineRequest.daysOfWeek, bus.getSeatNumber());
        drivingLine.setActiveDepartures(activeDepartures);

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
        DrivingLine drivingLine = this.drivingLineRepository.findById(id);
        drivingLine.setBus(null);
        this.drivingLineRepository.save(drivingLine);
        for (BusDeparture busDeparture: drivingLine.getBusDepartures()
             ) {
            this.busDepartureService.delete(busDeparture);
        }
        this.drivingLineRepository.deleteById(id);
    }

    public DrivingLine getById(int id) {
        return this.drivingLineRepository.findCompleteById(id);
    }

    public List<ActiveDeparture> getDays(DrivingLine drivingLine) {
        List<ActiveDeparture> ret = new ArrayList<>();
        for (ActiveDeparture a: drivingLine.getActiveDepartures()
             ){
            if (this.containsCheck(a, ret)){
                continue;
            } else {
                ret.add(a);
            }
        }

        Collections.sort(ret, (person1, person2) -> {
            if (person1.getDayOfWeek() == person2.getDayOfWeek()) {
                return person1.getDate().compareTo(person2.getDate());
            } else {
                return person1.getDayOfWeek().compareTo(person2.getDayOfWeek());
            }
        });

        return ret;
    }

    private boolean containsCheck(ActiveDeparture a, List<ActiveDeparture> ret) {
        for (ActiveDeparture a1 : ret
             ) {
            if (a.getDayOfWeek() == a1.getDayOfWeek()){
                return true;
            }
        }
        return false;
    }
}
