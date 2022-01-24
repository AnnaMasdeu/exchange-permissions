package edu.anna.exchangepermissions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exchange-permissions")
public class RatesController {

    private RatesRepository ratesRepository;

    public RatesController(RatesRepository ratesRepository) {
        this.ratesRepository = ratesRepository;
    }

    @ResponseStatus(value =  HttpStatus.OK)
    @GetMapping("/rates")
    public Iterable<Rate> getPermission() {

        return ratesRepository.findAll();
    }
}