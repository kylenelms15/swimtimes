package com.apex.swimtime.service;

import com.apex.swimtime.constants.*;
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
        List<Double> splits = new ArrayList<>();
        Split split = splitRepository.findById(timeID).get();

        splits.add(split.getSplit1());
        splits.add(split.getSplit2());
        splits.add(split.getSplit3());
        splits.add(split.getSplit4());
        splits.add(split.getSplit5());
        splits.add(split.getSplit6());
        splits.add(split.getSplit7());
        splits.add(split.getSplit8());
        splits.add(split.getSplit9());
        splits.add(split.getSplit10());
        splits.add(split.getSplit11());
        splits.add(split.getSplit12());
        splits.add(split.getSplit13());
        splits.add(split.getSplit14());
        splits.add(split.getSplit15());
        splits.add(split.getSplit16());
        splits.add(split.getSplit17());
        splits.add(split.getSplit18());
        splits.add(split.getSplit19());
        splits.add(split.getSplit20());
        splits.add(split.getSplit21());
        splits.add(split.getSplit22());
        splits.add(split.getSplit23());
        splits.add(split.getSplit24());
        splits.add(split.getSplit25());
        splits.add(split.getSplit26());
        splits.add(split.getSplit27());
        splits.add(split.getSplit28());
        splits.add(split.getSplit29());
        splits.add(split.getSplit30());
        splits.add(split.getSplit31());
        splits.add(split.getSplit32());
        splits.add(split.getSplit33());

        return splits.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    public List<SwimTimeRequest> addTimes(List<SwimTimeRequest> times) {
        for(SwimTimeRequest time :times) {
            addSwimTime(time);
        }

        return times;
    }

    public SwimTime addSwimTime(SwimTimeRequest time){
        //TODO: Better Data validation
        SwimTime entryTime = new SwimTime();

        if(time.getSwimmerID()!= null && time.getSwimmerID() >= 0) {
            entryTime.setSwimmerID(time.getSwimmerID());
        }

        if(time.getDate()!= null ) {
            entryTime.setDate(time.getDate());
        }

        if(time.getDistance() > 0) {
            entryTime.setDistance(time.getDistance());
        }

        if(time.getStroke() != null) {
            entryTime.setStroke(time.getStroke());

        }

        if(time.getTime() != null && time.getTime() >= 0) {
            entryTime.setTime(time.getTime());
        }

        swimTimeRepository.save(entryTime);

        if(time.getSplits() != null &&
                (time.getSplits().getSplit1() != null && time.getSplits().getSplit1() > 0))
        {
            Split entrySplits = time.getSplits();
            entrySplits.setTimeID(entryTime.getTimeID());
            splitRepository.save(entrySplits);
        }

        return entryTime;
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
