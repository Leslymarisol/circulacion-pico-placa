package com.validacioncirculacion.picoplacabackend.controller;




import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.validacioncirculacion.picoplacabackend.entity.Restriction;
import com.validacioncirculacion.picoplacabackend.repository.RestrictionRepository;

@RestController
@RequestMapping("/validation")
public class RestrictionController {

    @Autowired
    private RestrictionRepository restrictionRepository;



    public RestrictionController(RestrictionRepository restrictionRepository) {
        this.restrictionRepository = restrictionRepository;
    }



//Aqui paso : filtroDigito, Hora, Día
private String searchInfo(Integer filtro, String day, Time hour){

    String message = "";
    String dayRestriction = "";
    Time startTimeMorningRestriction;
    Time endTimeMorningRestriction;

    //Aqui envío solo el filtroDigito
    List<Restriction> result = restrictionRepository.search(filtro);

    //Aqui validar
    for(Restriction picoplaca: result){
        dayRestriction = picoplaca.getDay().getWeekday();
        startTimeMorningRestriction = picoplaca.getHours().getStartTimeMorning();
        endTimeMorningRestriction = picoplaca.getHours().getEndTimeMorning();

        if((day.equals(dayRestriction)) && !hour.before(startTimeMorningRestriction) && !hour.after(endTimeMorningRestriction)){
            message = "No puede circular";
        }else{
            message = "Es libre de circular";
        }
    }


    return message;
}

 

//Aqui recibo desde el frontEnd filtroDigito, hora, dia
@GetMapping("/searchInfo")
public ResponseEntity<?> searchDigit(@RequestParam Integer filtro, String day, Time hour){
    return ResponseEntity.status(HttpStatus.OK).body(searchInfo(filtro, day, hour));
}


    
}
