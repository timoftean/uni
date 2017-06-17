package ro.ubb.catalog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.model.Client;
import ro.ubb.catalog.core.model.Rent;
import ro.ubb.catalog.core.service.ClientService;
import ro.ubb.catalog.core.service.ClientService;
import ro.ubb.catalog.web.converter.RentConverter;
import ro.ubb.catalog.web.dto.RentDto;
import ro.ubb.catalog.web.dto.RentsDto;

import java.util.Map;
import java.util.Set;

/**
 * Created by Nicu on 4/9/17.
 */

@RestController
public class RentController {
    private static final Logger log = LoggerFactory.getLogger(RentController.class);

    @Autowired
    private ClientService clientService;

    @Autowired
    private RentConverter rentConverter;

    @RequestMapping(value = "/rents/{clientId}", method = RequestMethod.GET)
    public RentsDto getRents(
            @PathVariable final Long clientId) {
        log.trace("getRents: clientId={}", clientId);

        Client client = clientService.findClient(clientId);

        Set<Rent> rents = client.getRents();
        Set<RentDto> rentDtos = rentConverter
                .convertModelsToDtos(rents);


        RentsDto result = new RentsDto(rentDtos);

        log.trace("getRents: result={}", result);

        return result;
    }

    @RequestMapping(value = "/rents/{clientId}", method = RequestMethod.PUT)
    public RentsDto updateClientNocopies(
            @PathVariable final Long clientId,
            @RequestBody final RentsDto rentsDto) {
        log.trace("updateClientNocpoies: clientid={}, rentsDto={}",
                clientId, rentsDto);

        Map<Long, Integer> nocopies = rentConverter.convertDtoToMap(rentsDto);
        Client client = clientService.updateClientNocopies(clientId,nocopies);

        Set<RentDto> rentDtos = rentConverter.
                convertModelsToDtos(client.getRents());
        RentsDto result = new RentsDto(rentDtos);

        log.trace("getRents: result={}", result);

        return result;
    }

//    @RequestMapping(value = "/students/{studentId}", method = RequestMethod.PUT)
//    public Map<String, StudentDto> updateStudent(
//            @PathVariable final Long studentId,
//            @RequestBody final Map<String, StudentDto> studentDtoMap) {
//        log.trace("updateStudent: studentId={}, studentDtoMap={}", studentId, studentDtoMap);
//
//        StudentDto studentDto = studentDtoMap.get("student");
//        Student student = studentService.updateStudent(studentId, studentDto.getSerialNumber(),
//                studentDto.getName(), studentDto.getGroupNumber(), studentDto.getDisciplines());
//
//        Map<String, StudentDto> result = new HashMap<>();
//        result.put("student", studentConverter.convertModelToDto(student));
//
//        log.trace("updateStudent: result={}", result);
//
//        return result;
//    }
}

//@RestController
//public class RentController {
//
//    private static final Logger log = LoggerFactory.getLogger(RentController.class);
//
//    @Autowired
//    private RentService rentService;
//
//    @Autowired
//    private RentConverter rentConverter;
//
//    @RequestMapping(value = "/rents", method = RequestMethod.GET)
//    public RentsDto getRents() {
//        log.trace("getRents");
//
//        List<Rent> rents = rentService.findAll();
//
//        log.trace("getRents: rents={}", rents);
//
//        return new RentsDto(rentConverter.convertModelsToDtos(rents));
//    }
//
//    @RequestMapping(value = "/rents/{rentId}", method = RequestMethod.PUT)
//    public Map<String, RentDto> updateRent(
//            @PathVariable final Long rentId,
//            @RequestBody final Map<String, RentDto> rentDtoMap) {
//        log.trace("updateRent: rentId={}, rentDtoMap={}", rentId, rentDtoMap);
//
//        RentDto rentDto = rentDtoMap.get("rent");
//        Rent rent = rentService.updateRent(rentId, rentDto.getClientCnp(),rentDto.getClientTitle(),rentDto.getNoCopies());
//
//        Map<String, RentDto> result = new HashMap<>();
//        result.put("rent", rentConverter.convertModelToDto(rent));
//
//        log.trace("updateRents: result={}", result);
//
//        return result;
//    }
//
//    @RequestMapping(value = "/rents", method = RequestMethod.POST)
//    public Map<String, RentDto> createRent(
//            @RequestBody final Map<String, RentDto> rentDtoMap) {
//        log.trace("createRent: rentDtoMap={}", rentDtoMap);
//
//        RentDto rentDto = rentDtoMap.get("rent");
//        Rent rent = rentService.createRent(
//                rentDto.getClientCnp(),rentDto.getClientTitle(),rentDto.getNoCopies());
//
//        Map<String, RentDto> result = new HashMap<>();
//        result.put("rent", rentConverter.convertModelToDto(rent));
//
//        log.trace("updateRent: result={}", result);
//
//        return result;
//    }
//
//    @RequestMapping(value = "rents/{rentId}", method = RequestMethod.DELETE)
//    public ResponseEntity deleteRent(@PathVariable final Long rentId) {
//        log.trace("deleteRent: rentId={}", rentId);
//
//        rentService.deleteRent(rentId);
//
//        log.trace("deleteRent - method end");
//
//        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
//    }
//}
