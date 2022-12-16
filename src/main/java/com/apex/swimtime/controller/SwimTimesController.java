package com.apex.swimtime.controller;

import com.apex.swimtime.constants.*;
import com.apex.swimtime.repository.SwimmerRepository;
import com.apex.swimtime.service.SwimTimeService;
import com.apex.swimtime.service.SwimmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping(path="/demo")
public class SwimTimesController {
    //TODO: Change project path

    @Autowired
    private SwimmerService swimmerService;

    @Autowired
    private SwimTimeService swimTimeService;

    //TODO: change some of the pathVariables to

    @GetMapping(path="/allSwimmers")
    public @ResponseBody ResponseEntity<List<Swimmer>> getAllSwimmers() {

        return ResponseEntity.ok(swimmerService.getAllSwimmers());
    }

    @GetMapping(path="/swimmer/{swimmerID}")
    public @ResponseBody ResponseEntity<Swimmer> getSwimmer(@PathVariable Integer swimmerID) {

        return ResponseEntity.ok(swimmerService.getSwimmer(swimmerID));
    }

    @GetMapping(path="/times/{swimmerID}")
    public @ResponseBody ResponseEntity<List<SwimTimeRO>> getTimesBySwimmer(@PathVariable Integer swimmerID) {

        return ResponseEntity.ok(swimTimeService.getTimesBySwimmerID(swimmerID));
    }

    @GetMapping(path="/times/{distance}/{stroke}")
    public @ResponseBody ResponseEntity<List<SwimTimeRO>> getTimesByEvent(@PathVariable Integer distance, @PathVariable StrokeEnum stroke) {

        return ResponseEntity.ok(swimTimeService.getTimesByEvent(stroke, distance));
    }

    @GetMapping(path="/time/{timeID}")
    public @ResponseBody ResponseEntity<SwimTimeRO> getTimeByTimeID(@PathVariable Integer timeID) {

        return ResponseEntity.ok(swimTimeService.getTime(timeID));
    }

    @GetMapping(path="/split/{timeID}")
    public @ResponseBody ResponseEntity<Split> getSplitsByTimeID(@PathVariable Integer timeID) {

        return ResponseEntity.ok(swimTimeService.getSplitsByTimeID(timeID));
    }

    @PostMapping(path="/addSwimmer")
    public @ResponseBody ResponseEntity<Swimmer> addSwimmer(@RequestBody Swimmer swimmer) {

        return ResponseEntity.ok(swimmerService.addSwimmer(swimmer));
    }

    @PostMapping(path="/addSwimTime")
    public @ResponseBody ResponseEntity<SwimTime> addSwimTime(@RequestBody SwimTimeRO time) {

        return ResponseEntity.ok(swimTimeService.addSwimTime(time));
    }

    @PostMapping(path="/addSwimTimes")
    public @ResponseBody ResponseEntity<List<SwimTimeRO>> addMultipleTimes(@RequestBody List<SwimTimeRO> times) {
        //TODO: Change the return object
        return ResponseEntity.ok(swimTimeService.addTimes(times));
    }

    @DeleteMapping(path="/deleteTime/{timeID}")
    public @ResponseBody ResponseEntity<Integer> deleteTime(@PathVariable Integer timeID) {

        return ResponseEntity.ok(swimTimeService.deleteTime(timeID));
    }

    @DeleteMapping(path="/deleteTimes/{swimmerID}")
    public @ResponseBody ResponseEntity<Integer> deleteTimes(@PathVariable Integer swimmerID) {

        return ResponseEntity.ok(swimTimeService.deleteTimes(swimmerID));
    }

    @DeleteMapping(path="/deleteSwimmer/{swimmerID}")
    public @ResponseBody ResponseEntity<Integer> deleteSwimmer(@PathVariable Integer swimmerID) {
        Integer deletedID = swimTimeService.deleteSwimmer(swimmerID);
        return new ResponseEntity<>(deletedID, HttpStatus.OK);
    }
}