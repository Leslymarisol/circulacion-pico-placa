package com.validacioncirculacion.picoplacabackend.controller;

import java.sql.Time;
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
        Time startTimeMorningRestriction;
        Time endTimeMorningRestriction;
        Time starTimeNightRestriction;
        Time endTimeNightRestriction;

        List<Restriction> result = restrictionRepository.search(data.getLastDigit());

        try {

            LocalTime currentHour = LocalTime.now();

            LocalDateTime todayDate = LocalDateTime.now();
            // Definir el formato deseado
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            // Aplicar el formato a la fecha y hora actual
            String dateFormat = todayDate.format(format);

            Date currentDay = new SimpleDateFormat("dd-MM-yyyy").parse(dateFormat);

            // Fecha ingresada por el usuario
            Date userDate = new SimpleDateFormat("dd-MM-yyyy").parse(data.getDate());

            boolean hourBeforeCurrent = data.getHour().toLocalTime().isBefore(currentHour);

            boolean dateBeforeCurrent = userDate.before(currentDay);

            if (!dateBeforeCurrent && !hourBeforeCurrent) {
                // La fecha NO es anterior a la fecha actual
                DateFormat weekdayFormat = new SimpleDateFormat("EEEE", Locale.ENGLISH);
                for (Restriction picoplaca : result) {
                    dayRestriction = picoplaca.getDay().getWeekday();
                    startTimeMorningRestriction = picoplaca.getHours().getStartTimeMorning();
                    endTimeMorningRestriction = picoplaca.getHours().getEndTimeMorning();
                    starTimeNightRestriction = picoplaca.getHours().getStartTimeNight();
                    endTimeNightRestriction = picoplaca.getHours().getEndTimeNight();

                    if ((weekdayFormat.format(userDate).equals(dayRestriction))
                            && ((!data.getHour().before(startTimeMorningRestriction)
                                    && !data.getHour().after(endTimeMorningRestriction))
                                    || (!data.getHour().before(starTimeNightRestriction)
                                            && !data.getHour().after(endTimeNightRestriction)))) {
                        message = "No puede circular";
                    } else {
                        message = "Es libre de circular ";
                    }
                }

            } else {

                if (dateBeforeCurrent) {
                    message = "La fecha es ANTERIOR a la fecha actual ";
                }

                if (hourBeforeCurrent) {
                    message = "La hora es ANTERIOR a la hora actual ";
                }

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
