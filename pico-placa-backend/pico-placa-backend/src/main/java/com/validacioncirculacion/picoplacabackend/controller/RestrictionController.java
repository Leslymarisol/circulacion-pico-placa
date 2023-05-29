package com.validacioncirculacion.picoplacabackend.controller;

import java.sql.Time;
import java.util.List;

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

        for (Restriction picoplaca : result) {
            dayRestriction = picoplaca.getDay().getWeekday();
            startTimeMorningRestriction = picoplaca.getHours().getStartTimeMorning();
            endTimeMorningRestriction = picoplaca.getHours().getEndTimeMorning();
            starTimeNightRestriction = picoplaca.getHours().getStartTimeNight();
            endTimeNightRestriction = picoplaca.getHours().getEndTimeNight();

            if ((data.getWeekday().equals(dayRestriction)) && ((!data.getHour().before(startTimeMorningRestriction)
                    && !data.getHour().after(endTimeMorningRestriction))
                    || (!data.getHour().before(starTimeNightRestriction)
                    && !data.getHour().after(endTimeNightRestriction)))) {
                message = "No puede circular";
            } else {
                message = "Es libre de circular";
            }
        }

        return message;
    }

    @PostMapping("/search")
    public ResponseEntity<?> postData(@RequestBody Data data) {
        return ResponseEntity.status(HttpStatus.OK).body(validateData(data));
    }

}
