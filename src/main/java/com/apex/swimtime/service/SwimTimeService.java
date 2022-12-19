package com.apex.swimtime.service;

import com.apex.swimtime.constants.*;
import com.apex.swimtime.model.Split;
import com.apex.swimtime.model.SwimTime;
import com.apex.swimtime.model.SwimTimeRequest;
import com.apex.swimtime.model.SwimTimeResponse;
import com.apex.swimtime.repository.SplitRepository;
import com.apex.swimtime.repository.SwimTimeRepository;
import com.apex.swimtime.repository.SwimmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SwimTimeService {

    @Autowired
    private SwimTimeRepository swimTimeRepository;

    @Autowired
    private SplitRepository splitRepository;

    @Autowired
    private SwimmerRepository swimmerRepository;

    //TODO: rename
    public List<SwimTimeResponse> getTimesByObject(Integer swimmerID, StrokeEnum stroke, Integer distance, Date startDate, Date endDate) {
        Integer strokeValue = null;

        if(stroke != null) {
            strokeValue = stroke.ordinal();
        }

        List<Integer> timeIDs = swimTimeRepository.findTime(swimmerID, strokeValue, distance);

        return getTimes(timeIDs);
    }

    private List<SwimTimeResponse> getTimes(List<Integer> timeIDs) {
        List<SwimTimeResponse> times = new ArrayList<>();

        for(Integer timeID : timeIDs) {
            times.add(getTime(timeID));
        }

        return times;
    }

    private SwimTimeResponse getTime(Integer timeID) {
        SwimTimeResponse time = new SwimTimeResponse();

        SwimTime swimTime = swimTimeRepository.findById(timeID).get();
        time.setSwimmerID(swimTime.getSwimmerID());
        time.setDate(swimTime.getDate());
        time.setTime(swimTime.getTime());
        time.setStroke(swimTime.getStroke());
        time.setDistance(swimTime.getDistance());
        time.setTimeID(swimTime.getTimeID());
        time.setSplits(getSplitsAsList(swimTime.getTimeID()));

        return time;
    }

    private List<Double> getSplitsAsList(Integer timeID) {

        if(splitRepository.findById(timeID).isPresent()) {
            Split splitObject = splitRepository.findById(timeID).get();

            List<Double> splits = Arrays.asList(splitObject.getSplits());

            return splits.stream().filter(Objects::nonNull).collect(Collectors.toList());
        }

        return null;
    }

    public List<SwimTimeRequest> addTimes(List<SwimTimeRequest> times) {
        for(SwimTimeRequest time :times) {
            addSwimTime(time);
        }

        return times;
    }

    //TODO: Use Mapstruct
    public void addSwimTime(SwimTimeRequest time){
        SwimTime entryTime = new SwimTime();

        entryTime.setSwimmerID(time.getSwimmerID());
        entryTime.setDate(time.getDate());
        entryTime.setDistance(time.getDistance());
        entryTime.setStroke(time.getStroke());
        entryTime.setTime(time.getTime());
        swimTimeRepository.save(entryTime);

        Split entrySplits = time.getSplits();
        if(entrySplits != null && entrySplits.getSplits().length>0) {
            entrySplits.setTimeID(entryTime.getTimeID());
            splitRepository.save(entrySplits);
        }
        System.out.println("test");
    }

    public Integer deleteTime(Integer timeID) {
        if(swimTimeRepository.findById(timeID).isPresent()) {

            if(splitRepository.findById(timeID).isPresent()) {
                splitRepository.deleteById(timeID);
            }

            swimTimeRepository.deleteById(timeID);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Time not found.");
    }

    public Integer deleteTimes(Integer swimmerID) {
        validateSwimmer(swimmerID);
        List<Integer> timeIDs = swimTimeRepository.findBySwimmerID(swimmerID);

        if(timeIDs.size() > 0) {
            for(Integer timeID : timeIDs) {
                deleteTime(timeID);
            }
        }

        return swimmerID;
    }

    public Integer deleteSwimmer(Integer swimmerID) {
        if(validateSwimmer(swimmerID)) {
            deleteTimes(swimmerID);
            swimmerRepository.deleteById(swimmerID);

            return swimmerID;
        }
        return null;
    }

    private boolean validateSwimmer(Integer swimmerID) {
        if(swimmerRepository.findById(swimmerID).isPresent()) {
            return true;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Swimmer not found.");
    }
}
