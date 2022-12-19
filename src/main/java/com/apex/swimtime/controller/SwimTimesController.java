package com.apex.swimtime.controller;

import com.apex.swimtime.constants.*;
import com.apex.swimtime.model.SwimTime;
import com.apex.swimtime.model.SwimTimeRequest;
import com.apex.swimtime.model.SwimTimeResponse;
import com.apex.swimtime.model.Swimmer;
import com.apex.swimtime.service.SwimTimeService;
import com.apex.swimtime.service.SwimmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path="/demo")
public class SwimTimesController {
    //TODO: Change project path

    @Autowired
    private SwimmerService swimmerService;

    @Autowired
    private SwimTimeService swimTimeService;

    @GetMapping(path="/allSwimmers")
    public @ResponseBody ResponseEntity<List<Swimmer>> getAllSwimmers() {

        return ResponseEntity.ok(swimmerService.getAllSwimmers());
    }

    @GetMapping(path="/swimmer/{swimmerID}")
    public @ResponseBody ResponseEntity<Swimmer> getSwimmer(@PathVariable Integer swimmerID) {

        return ResponseEntity.ok(swimmerService.getSwimmer(swimmerID));
    }

    @GetMapping(path="/times")
    public @ResponseBody ResponseEntity<List<SwimTimeResponse>> getTimesByObject(@RequestParam(required = false) Integer swimmerID,
                                                                                 @RequestParam(required = false) StrokeEnum stroke,
                                                                                 @RequestParam(required = false) Integer distance,
                                                                                 @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                                                 @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {

        return ResponseEntity.ok(swimTimeService.getTimesByObject(swimmerID, stroke, distance, startDate, endDate));
    }

    @PostMapping(path="/addSwimmer")
    public @ResponseBody ResponseEntity addSwimmer(@RequestBody Swimmer swimmer) {
        swimmerService.addSwimmer(swimmer);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path="/addSwimTime")
    public @ResponseBody ResponseEntity addSwimTime(@RequestBody SwimTimeRequest time) {
        swimTimeService.addSwimTime(time);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path="/addSwimTimes")
    public @ResponseBody ResponseEntity addMultipleTimes(@RequestBody List<SwimTimeRequest> times) {
        swimTimeService.addTimes(times);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path="/deleteTime/{timeID}")
    public @ResponseBody ResponseEntity<Integer> deleteTime(@PathVariable Integer timeID) {
        //TODO: change to RequestParam
        return ResponseEntity.ok(swimTimeService.deleteTime(timeID));
    }

    @DeleteMapping(path="/deleteTimes/{swimmerID}")
    public @ResponseBody ResponseEntity<Integer> deleteTimes(@PathVariable Integer swimmerID) {
        //TODO: change to RequestParam
        return ResponseEntity.ok(swimTimeService.deleteTimes(swimmerID));
    }

    @DeleteMapping(path="/deleteSwimmer/{swimmerID}")
    public @ResponseBody ResponseEntity<Integer> deleteSwimmer(@PathVariable Integer swimmerID) {
        Integer deletedID = swimTimeService.deleteSwimmer(swimmerID);
        return new ResponseEntity<>(deletedID, HttpStatus.OK);
    }

    @DeleteMapping(path="/deleteSwimmers")
    public @ResponseBody ResponseEntity<Integer> deleteSwimmers(@RequestBody List<Integer> swimmerIDs) {
        swimTimeService.deleteSwimmers(swimmerIDs);
        return ResponseEntity.ok().build();
    }
}