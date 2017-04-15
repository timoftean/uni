package ro.ubb.catalog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.ubb.catalog.core.model.Rent;
import ro.ubb.catalog.core.service.RentService;
import ro.ubb.catalog.web.dto.RentsDto;

import java.util.Set;

/**
 * Created by Nicu on 4/9/17.
 */
@RestController
public class RentController {

    private static final Logger log = LoggerFactory.getLogger(RentController.class);

    @Autowired
    private RentService rentController;

    @RequestMapping(value="rents", produces = MediaType.APPLICATION_JSON_VALUE)
    public RentsDto getDisciplines() {
        log.trace("getRents --- method entered");

        Set<Rent> rents = rentController.getAllRents();

        log.trace("getRents: rents={}", rents);

        return new RentsDto(rents);
    }
}
