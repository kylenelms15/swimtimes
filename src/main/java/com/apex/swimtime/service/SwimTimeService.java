package com.apex.swimtime.service;

import com.apex.swimtime.constants.Split;
import com.apex.swimtime.constants.SwimTime;
import com.apex.swimtime.constants.SwimTimeRO;
import com.apex.swimtime.repository.SplitRepository;
import com.apex.swimtime.repository.SwimTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SwimTimeService {

    @Autowired
    private SwimTimeRepository swimTimeRepository;

    @Autowired
    private SplitRepository splitRepository;


    public SwimTime addSwimTime(SwimTimeRO time){
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
}
