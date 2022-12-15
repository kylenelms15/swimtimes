package com.apex.swimtime.controller;

import com.apex.swimtime.constants.SwimTime;
import com.apex.swimtime.constants.SwimTimeRO;
import com.apex.swimtime.constants.Swimmer;
import com.apex.swimtime.repository.SwimmerRepository;
import com.apex.swimtime.service.SwimTimeService;
import com.apex.swimtime.service.SwimmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping(path="/demo")
public class SwimTimesController {

    @Autowired
    private SwimmerRepository swimmerRepository;

    @Autowired
    private SwimmerService swimmerService;

    @Autowired
    private SwimTimeService swimTimeService;

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping(path="/all")
    public @ResponseBody ResponseEntity<List<Swimmer>> getAllSwimmers() {

        return ResponseEntity.ok(swimmerRepository.findAll());
    }

    @PostMapping(path="/addSwimmer")
    public @ResponseBody ResponseEntity<Swimmer> addSwimmer(@RequestBody Swimmer swimmer) {

        return ResponseEntity.ok(swimmerService.addSwimmer(swimmer));
    }

    @PostMapping(path="/addSwimTime")
    public @ResponseBody ResponseEntity<SwimTime> addSwimTime(@RequestBody SwimTimeRO time) {

        return ResponseEntity.ok(swimTimeService.addSwimTime(time));
    }
}