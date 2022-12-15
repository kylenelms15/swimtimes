package com.apex.swimtime.service;

import com.apex.swimtime.constants.Swimmer;
import com.apex.swimtime.repository.SwimmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SwimmerService {

    @Autowired
    private SwimmerRepository swimmerRepository;


    public Swimmer addSwimmer(Swimmer swimmer){
        swimmerRepository.save(swimmer);
        return swimmer;
    }
}
