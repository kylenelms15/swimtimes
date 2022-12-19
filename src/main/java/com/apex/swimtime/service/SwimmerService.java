package com.apex.swimtime.service;

import com.apex.swimtime.model.Swimmer;
import com.apex.swimtime.repository.SwimmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SwimmerService {

    @Autowired
    private SwimmerRepository swimmerRepository;


    public List<Swimmer> getAllSwimmers() {
        return swimmerRepository.findAll();
    }

    public Swimmer getSwimmer(Integer swimmerID) {
        Optional<Swimmer> swimmer = swimmerRepository.findById(swimmerID);
        if(swimmer.isPresent()) {
            return swimmer.get();
        }
        return null;
    }

    public void addSwimmer(Swimmer swimmer){
        swimmerRepository.save(swimmer);
    }
}
