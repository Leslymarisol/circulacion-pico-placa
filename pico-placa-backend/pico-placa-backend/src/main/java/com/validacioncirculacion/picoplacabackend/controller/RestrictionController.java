package com.validacioncirculacion.picoplacabackend.controller;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.validacioncirculacion.picoplacabackend.entity.Data;
import com.validacioncirculacion.picoplacabackend.entity.Restriction;
import com.validacioncirculacion.picoplacabackend.repository.RestrictionRepository;

@RestController
@RequestMapping("/picoplaca")
public class RestrictionController {

    @Autowired
    private RestrictionRepository restrictionRepository;

    public RestrictionController(RestrictionRepository restrictionRepository) {
        this.restrictionRepository = restrictionRepository;
    }

    private String validateData(Data data) {
        String message = "";
        String dayRestriction = "";
        LocalTime startTimeMorningRestriction;
        LocalTime endTimeMorningRestriction;
        LocalTime starTimeNightRestriction;
        LocalTime endTimeNightRestriction;

        List<Restriction> result = restrictionRepository.search(data.getLastDigit());

        try {

            LocalTime currentHour = LocalTime.now();
            LocalTime userHour = LocalTime.parse(data.getHour());

            LocalDateTime todayDate = LocalDateTime.now();
            // Definir el formato deseado
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            // Aplicar el formato a la fecha y hora actual
            String dateFormat = todayDate.format(format);

            Date currentDay = new SimpleDateFormat("yyyy-MM-dd").parse(dateFormat);

            // Fecha ingresada por el usuario
            Date userDate = new SimpleDateFormat("yyyy-MM-dd").parse(data.getDate());
 
            
            boolean hourBeforeCurrent = userHour.isBefore(currentHour);

            boolean dateBeforeCurrent = userDate.before(currentDay);

            if ((!dateBeforeCurrent && !hourBeforeCurrent) || (!dateBeforeCurrent && hourBeforeCurrent)) {
                // La fecha NO es anterior a la fecha actual
                DateFormat weekdayFormat = new SimpleDateFormat("EEEE", Locale.ENGLISH);
                for (Restriction picoplaca : result) {
                    dayRestriction = picoplaca.getDay().getWeekday();
                    startTimeMorningRestriction = picoplaca.getHours().getStartTimeMorning().toLocalTime();
                    endTimeMorningRestriction = picoplaca.getHours().getEndTimeMorning().toLocalTime();
                    starTimeNightRestriction = picoplaca.getHours().getStartTimeNight().toLocalTime();
                    endTimeNightRestriction = picoplaca.getHours().getEndTimeNight().toLocalTime();

                    boolean morningSchedule = (!userHour.isBefore(startTimeMorningRestriction)
                            && !userHour.isAfter(endTimeMorningRestriction));
                    boolean nightSchedule = (!userHour.isBefore(starTimeNightRestriction)
                            && !userHour.isAfter(endTimeNightRestriction));

                    if ((weekdayFormat.format(userDate).equals(dayRestriction))
                            && (morningSchedule || nightSchedule)) {
                        message = "El vehículo no circula";
                    } else {
                        message = "El vehículo puede circular";
                    }
                }

            } 

                if (dateBeforeCurrent) {
                    message = "La fecha es anterior a la actual, ingrese nuevamente.";
                }

                if (hourBeforeCurrent && userDate.equals(currentDay) ) {
                    message = "La hora es anterior a la actual, ingrese nuevamente. ";
                }

            

        } catch (Exception e) {
            System.out.println("Something bad has occurred");
        }

        return message;
    }

    @PostMapping("/search")
    public ResponseEntity<?> postData(@RequestBody Data data) {
        return ResponseEntity.status(HttpStatus.OK).body(validateData(data));
    }

}
