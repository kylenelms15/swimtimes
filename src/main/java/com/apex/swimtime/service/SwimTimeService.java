package com.apex.swimtime.service;

import com.apex.swimtime.constants.SwimTime;
import com.apex.swimtime.repository.SwimTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SwimTimeService {

    @Autowired
    private SwimTimeRepository swimTimeRepository;


    public SwimTime addSwimTime(SwimTime time){
        swimTimeRepository.save(time);
        return time;
    }
}
